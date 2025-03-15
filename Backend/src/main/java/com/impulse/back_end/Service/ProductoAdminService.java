package com.impulse.back_end.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.impulse.back_end.Dto.CaracteristicaPeticionDTO;
import com.impulse.back_end.Dto.ProductoPeticionDTO;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.CaracteristicaEntity;
import com.impulse.back_end.Entity.ImagenEntity;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.exception.ProductoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static com.impulse.back_end.mapper.ProductoMapper.*;

@Service
public class ProductoAdminService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private CategoriaService categoriaService;

    private final Logger logger = LoggerFactory.getLogger(ProductoAdminService.class);

    public ProductoRespuestaDTO registrarProducto(
            String sessionId,
            String peticion,
            MultipartFile imagen
    ) throws ProductoException, JsonProcessingException {
        logger.info("registrarProducto --Session:[{}] --Peticion:[{}] --Imagen:[{}]",
                sessionId, peticion, imagen.getOriginalFilename()
        );

        ProductoPeticionDTO productoPeticionDTO = mapProductoPeticionDTO(peticion);

        validarPeticion(productoPeticionDTO);
        validarExtension(imagen);
        validarSession(sessionId, "Usuario no autorizado para registrar productos");
        validarProductoPorNombre(productoPeticionDTO.getNombre());

        ImagenEntity imagenEntity = imagenService.subirImagen(imagen);
        if (imagenEntity == null) {
            throw new ProductoException(HttpStatus.CONFLICT, "imagen_no_subida", "Ocurrio un error al subir la imagen");
        }

        return mapProductoRespuestaDTO(
                productoRepository.save(
                        mapNewProductoEntity(
                                productoPeticionDTO,
                                imagenEntity,
                                categoriaService.consultarOCrear(productoPeticionDTO.getCategoria())
                        )
                )
        );
    }

    public ProductoRespuestaDTO modificarProductoPorId(
            String sessionId,
            Long id,
            String peticion,
            MultipartFile imagen
    ) throws ProductoException, JsonProcessingException {
        logger.info("modificarProductoPorId --Session:[{}] --Id:[{}] --Peticion:[{}] --Imagen:[{}]",
                sessionId, id, peticion, (imagen == null ? "null" : imagen.getOriginalFilename())
        );

        ProductoPeticionDTO productoPeticionDTO = mapProductoPeticionDTO(peticion);

        validarPeticion(productoPeticionDTO);
        validarExtension(imagen);
        validarSession(sessionId, "Usuario no autorizado para modificar productos");


        ProductoEntity productoEntityAModificar = consultarProductoPorId(id);
        ProductoEntity productoEntityPorNombre = consultarProductoPorNombre(productoPeticionDTO.getNombre());
        if (productoEntityPorNombre != null && !Objects.equals(productoEntityPorNombre.getId(), id)) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
        }

        ImagenEntity imagenEntity = null;
        if (imagen != null) {
            List<ImagenEntity> imagenesEntities = new CopyOnWriteArrayList<>(productoEntityAModificar.getImagenes());
            for (ImagenEntity imagenEntityInterna : imagenesEntities) {
                productoEntityAModificar.removeImagen(imagenEntityInterna);
            }

            imagenEntity = imagenService.subirImagen(imagen);
            if (imagenEntity == null) {
                throw new ProductoException(HttpStatus.CONFLICT, "imagen_no_subida", "Ocurrio un error al subir la imagen");
            }
        }

        List<CaracteristicaEntity> caracteristicaEntities = new CopyOnWriteArrayList<>(productoEntityAModificar.getCaracteristicas());
        for (CaracteristicaEntity caracteristicaEntity : caracteristicaEntities) {
            productoEntityAModificar.removeCaracteristica(caracteristicaEntity);
        }

        productoEntityAModificar.setNombre(productoPeticionDTO.getNombre());
        productoEntityAModificar.setDescripcion(productoPeticionDTO.getDescripcion());
        productoEntityAModificar.setPrecioAlquiler(productoPeticionDTO.getPrecioAlquiler());
        productoEntityAModificar.setCategoria(categoriaService.consultarOCrear(productoPeticionDTO.getCategoria()));

        productoRepository.save(productoEntityAModificar);

        if (imagenEntity != null) {
            productoEntityAModificar.addImagen(imagenEntity);
        }

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
        logger.info("eliminarProductoPorId --Session:[{}] --Id:[{}]", sessionId, id);

        validarSession(sessionId, "Usuario no autorizado para eliminar productos");

        productoRepository.deleteById(consultarProductoPorId(id).getId());
    }

    private void validarPeticion(ProductoPeticionDTO peticion) throws ProductoException {
        Set<ConstraintViolation<ProductoPeticionDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(peticion);
        for (ConstraintViolation<ProductoPeticionDTO> violation : violations) {
            logger.error("validarPeticion --Peticion:[{}] --Error:[{}]", peticion, violation.getMessage());
        }
        if (!violations.isEmpty())
            throw new ProductoException(HttpStatus.BAD_REQUEST, "producto_invalido", violations.stream().map(v -> v.getMessage()).collect(Collectors.joining(", ")));
    }

    private void validarSession(String sessionId, String mensaje) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId))
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", mensaje);
    }

    private void validarProductoPorNombre(String nombre) throws ProductoException {
        if (productoRepository.findByNombre(nombre).isPresent())
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
    }

    private void validarExtension(MultipartFile imagen) throws ProductoException {
        if (imagen != null) {
            boolean extensionesValidas = imagen.getOriginalFilename().toLowerCase().endsWith("png")
                    || imagen.getOriginalFilename().toLowerCase().endsWith("jpeg")
                    || imagen.getOriginalFilename().toLowerCase().endsWith("jpg");
            if (!extensionesValidas)
                throw new ProductoException(HttpStatus.BAD_REQUEST, "imagenes_invalidas", "Solo se permiten archivos con extension *.png o *.jpeg");
        }
    }

    private ProductoEntity consultarProductoPorId(Long id) throws ProductoException {
        return productoRepository.findById(id).orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado"));
    }

    private ProductoEntity consultarProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre).orElse(null);
    }
}