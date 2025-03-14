package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.CaracteristicaPeticionDTO;
import com.impulse.back_end.Dto.ImagenRespuestaDTO;
import com.impulse.back_end.Dto.ProductoPeticionDTO;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.CaracteristicaEntity;
import com.impulse.back_end.Entity.ImagenEntity;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.CaracteristicaRepository;
import com.impulse.back_end.Repository.ImagenRepository;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.exception.ProductoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.impulse.back_end.mapper.ProductoMapper.mapProductoRespuestaDTO;

@Service
public class ProductoAdminService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ImagenService imagenService;

    private final Logger logger = LoggerFactory.getLogger(ProductoAdminService.class);

    public ProductoRespuestaDTO registrarProducto(
            String sessionId,
            ProductoPeticionDTO productoPeticionDTO
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para registrar productos");
        }

        Optional<ProductoEntity> productoOptional = productoRepository.findByNombre(productoPeticionDTO.getNombre());
        if (productoOptional.isPresent()) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
        }

        ProductoEntity productoEntity = new ProductoEntity(
                productoPeticionDTO.getNombre(),
                productoPeticionDTO.getDescripcion(),
                productoPeticionDTO.getPrecioAlquiler()
        );

        for (CaracteristicaPeticionDTO caracteristica: productoPeticionDTO.getCaracteristicas()) {
            productoEntity.addCaracteristica(new CaracteristicaEntity(
                    caracteristica.getNombre(),
                    caracteristica.getDescripcion()
            ));
        }

        return mapProductoRespuestaDTO(productoRepository.save(productoEntity));
    }

    public ProductoRespuestaDTO modificarProductoPorId(
            String sessionId,
            Long id,
            ProductoPeticionDTO productoPeticionDTO
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para modificar productos");
        }

        Optional<ProductoEntity> productoOptional1 = productoRepository.findById(id);
        if (productoOptional1.isEmpty())
            throw new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado");

        Optional<ProductoEntity> productoOptional2 = productoRepository.findByNombre(productoPeticionDTO.getNombre());
        if (productoOptional2.isPresent() && !Objects.equals(productoOptional2.get().getId(), id)) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
        }

        ProductoEntity productoEntityAModificar = productoOptional1.get();

        List<CaracteristicaEntity> caracteristicaEntities = new CopyOnWriteArrayList<>(productoEntityAModificar.getCaracteristicas());

        for (CaracteristicaEntity caracteristicaEntity : caracteristicaEntities) {
            productoEntityAModificar.removeCaracteristica(caracteristicaEntity);
        }

        productoEntityAModificar.setNombre(productoPeticionDTO.getNombre());
        productoEntityAModificar.setDescripcion(productoPeticionDTO.getDescripcion());
        productoEntityAModificar.setPrecioAlquiler(productoPeticionDTO.getPrecioAlquiler());

        productoRepository.save(productoEntityAModificar);

        for (CaracteristicaPeticionDTO caracteristica: productoPeticionDTO.getCaracteristicas()) {
            productoEntityAModificar.addCaracteristica(new CaracteristicaEntity(
                    caracteristica.getNombre(),
                    caracteristica.getDescripcion()
            ));
        }

        return mapProductoRespuestaDTO(productoRepository.save(productoEntityAModificar));
    }

    public void eliminarProductoPorId(
            String sessionId,
            Long id
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para eliminar productos");
        }

        Optional<ProductoEntity> productoOptional1 = productoRepository.findById(id);
        if (productoOptional1.isEmpty())
            throw new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado");

        productoRepository.deleteById(id);
    }

    public List<ImagenRespuestaDTO> subirImagen(
            String sessionId,
            Long id,
            List<MultipartFile> imagenes
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para subir imagenes");
        }

        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty()) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el id especificado no existe");
        }

        boolean extensionesValidas = imagenes.stream().allMatch(imagen -> {
            return imagen.getOriginalFilename().toLowerCase().endsWith("png")
            || imagen.getOriginalFilename().toLowerCase().endsWith("jpeg")
            || imagen.getOriginalFilename().toLowerCase().endsWith("jpg")
            ;
        });
        if (!extensionesValidas) {
            throw new ProductoException(HttpStatus.BAD_REQUEST, "imagenes_invalidas", "Solo se permiten archivos con extension *.png o *.jpeg");
        }

        ProductoEntity productoEntity = productoOptional.get();
        List<ImagenRespuestaDTO> listaImagenes = new ArrayList<>();

        for (MultipartFile imagen : imagenes) {
            String ruta = imagenService.subirImagen(imagen);
            if (ruta != null) {
                ImagenEntity imagenEntity = new ImagenEntity(ruta);
                imagenEntity.setProducto(productoEntity);
                productoEntity.addImagen(imagenEntity);
                imagenRepository.save(imagenEntity);
                listaImagenes.add(new ImagenRespuestaDTO(imagenEntity.getId(), imagenEntity.getRuta()));
            }
        }

        productoRepository.save(productoEntity);

        return listaImagenes;
    }

    public void borrarImagen(
            String sessionId,
            Long id,
            Long imagenId
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para borrar imagenes");
        }

        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty()) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el id especificado no existe");
        }

        ProductoEntity productoEntity = productoOptional.get();
        ImagenEntity imagenEntityRemover = null;

        for (ImagenEntity imagenEntity : productoEntity.getImagenes()) {
            if (imagenEntity.getId() == imagenId) {
                logger.info("Imagen a remover ImagenEntity(id={}, ruta={}, producto.id={})", imagenEntity.getId(), imagenEntity.getRuta(), imagenEntity.getProducto().getId());
                imagenEntityRemover= imagenEntity;
                break;
            }
        }

        if (imagenEntityRemover != null) {
            imagenService.borrarImagen(imagenEntityRemover.getRuta());
            productoEntity.removeImagen(imagenEntityRemover);
        }

        productoRepository.save(productoEntity);
    }

    public void borrarImagenes(
            String sessionId,
            Long id
    ) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", "Usuario no autorizado para borrar imagenes");
        }

        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty()) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el id especificado no existe");
        }

        ProductoEntity productoEntity = productoOptional.get();
        List<ImagenEntity> imagenesEntityRemover = new ArrayList<>();

        imagenesEntityRemover.addAll(productoEntity.getImagenes());

        for (ImagenEntity imagenEntity : imagenesEntityRemover) {
            imagenService.borrarImagen(imagenEntity.getRuta());
            productoEntity.removeImagen(imagenEntity);
        }

        productoRepository.save(productoEntity);
    }
}