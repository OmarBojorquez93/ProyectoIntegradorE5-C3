package com.impulse.back_end.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.impulse.back_end.Dto.CaracteristicaPeticionDTO;
import com.impulse.back_end.Dto.ProductoPeticionDTO;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.CaracteristicaEntity;
import com.impulse.back_end.Entity.ImagenEntity;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.ImagenRepository;
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

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.impulse.back_end.mapper.ProductoMapper.*;

@Service
public class ProductoAdminService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenRepository imagenRepository;

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
        logger.info("Iniciando registro de producto... --Session:[{}] --Peticion:[{}] --Imagen:[{}]", sessionId, peticion, (imagen != null ? imagen.getOriginalFilename() : "Ninguna"));

        ProductoPeticionDTO productoPeticionDTO = mapProductoPeticionDTO(peticion);

        validarPeticion(productoPeticionDTO);
        validarExtension(imagen);
        validarSession(sessionId, "Usuario no autorizado para registrar productos");
        validarProductoPorNombre(productoPeticionDTO.getNombre());

        ImagenEntity imagenEntity = new ImagenEntity();
        imagenEntity.setRuta(imagen.getOriginalFilename());

        // Guardar la imagen antes
        imagenEntity = imagenRepository.save(imagenEntity); 
        System.out.println("ID de la imagen guardada: " + imagenEntity.getId());

        ProductoEntity producto = mapNewProductoEntity(
                productoPeticionDTO,
                imagenEntity,
                categoriaService.consultarOCrear(productoPeticionDTO.getCategoria())
        );

        producto = productoRepository.save(producto);
        logger.info("Producto guardado con ID: {}", producto.getId());

        if (imagen == null || imagen.isEmpty()) {
            logger.error("No se recibió ninguna imagen en la petición.");
            throw new ProductoException(HttpStatus.BAD_REQUEST, "imagen_no_proporcionada", "Debe proporcionar una imagen para el producto");
        }

        logger.info("Subiendo imagen para el producto ID: {}", producto.getId());
        imagenEntity = imagenService.subirImagen(imagen, producto.getId());

        if (imagenEntity == null) {
            logger.error("Error al subir la imagen para el producto ID: {}", producto.getId());
            throw new ProductoException(HttpStatus.CONFLICT, "imagen_no_subida", "Ocurrió un error al subir la imagen");
        }
        producto.setImagen(imagenEntity);
        logger.info("Imagen subida correctamente: {}", imagenEntity.getRuta());

        return mapProductoRespuestaDTO(productoRepository.save(producto));
    }

    public ProductoRespuestaDTO modificarProductoPorId(
            String sessionId,
            Long id,
            String peticion,
            MultipartFile imagen
    ) throws ProductoException, JsonProcessingException {
        logger.info("modificarProductoPorId --Session:[{}] --Id:[{}] --Peticion:[{}] --Imagen:[{}]", sessionId, id, peticion, (imagen == null ? "null" : imagen.getOriginalFilename()));

        ProductoPeticionDTO productoPeticionDTO = mapProductoPeticionDTO(peticion);

        validarPeticion(productoPeticionDTO);
        validarExtension(imagen);
        validarSession(sessionId, "Usuario no autorizado para modificar productos");

        ProductoEntity productoEntity = consultarProductoPorId(id);
        ProductoEntity productoPorNombre = consultarProductoPorNombre(productoPeticionDTO.getNombre());
        if (productoPorNombre != null && !Objects.equals(productoPorNombre.getId(), id)) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
        }

        if (imagen != null) {
            imagenService.borrarImagen(productoEntity.getImagen().getRuta());
            ImagenEntity nuevaImagen = imagenService.subirImagen(imagen, id);
            if (nuevaImagen == null) {
                throw new ProductoException(HttpStatus.CONFLICT, "imagen_no_subida", "Ocurrió un error al subir la nueva imagen");
            }
            productoEntity.setImagen(nuevaImagen);
        }

        productoEntity.setNombre(productoPeticionDTO.getNombre());
        productoEntity.setDescripcion(productoPeticionDTO.getDescripcion());
        productoEntity.setPrecioAlquiler(productoPeticionDTO.getPrecioAlquiler());
        productoEntity.setCategoria(categoriaService.consultarOCrear(productoPeticionDTO.getCategoria()));
        
        productoEntity.getCaracteristicas().clear();
        for (CaracteristicaPeticionDTO caracteristica : productoPeticionDTO.getCaracteristicas()) {
            productoEntity.addCaracteristica(new CaracteristicaEntity(
                    caracteristica.getNombre(),
                    caracteristica.getDescripcion()
            ));
        }

        return mapProductoRespuestaDTO(productoRepository.save(productoEntity));
    }

    public void eliminarProductoPorId(String sessionId, Long id) throws ProductoException {
        logger.info("eliminarProductoPorId --Session:[{}] --Id:[{}]", sessionId, id);
        validarSession(sessionId, "Usuario no autorizado para eliminar productos");
        ProductoEntity producto = consultarProductoPorId(id);
        imagenService.borrarImagen(producto.getImagen().getRuta());
        productoRepository.deleteById(id);
    }

    private void validarPeticion(ProductoPeticionDTO peticion) throws ProductoException {
        Set<ConstraintViolation<ProductoPeticionDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(peticion);
        if (!violations.isEmpty()) {
            throw new ProductoException(HttpStatus.BAD_REQUEST, "producto_invalido", violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
        }
    }

    private void validarSession(String sessionId, String mensaje) throws ProductoException {
        if (!sessionService.isAdminSession(sessionId)) {
            throw new ProductoException(HttpStatus.UNAUTHORIZED, "usuario_no_autorizado", mensaje);
        }
    }

    private void validarProductoPorNombre(String nombre) throws ProductoException {
        if (productoRepository.findByNombre(nombre).isPresent()) {
            throw new ProductoException(HttpStatus.CONFLICT, "producto_invalido", "El producto con el nombre especificado ya existe");
        }
    }

    private void validarExtension(MultipartFile imagen) throws ProductoException {
        if (imagen != null && !imagen.getOriginalFilename().toLowerCase().matches(".*\\.(png|jpeg|jpg)$")) {
            throw new ProductoException(HttpStatus.BAD_REQUEST, "imagenes_invalidas", "Solo se permiten archivos con extension *.png, *.jpeg o *.jpg");
        }
    }

    private ProductoEntity consultarProductoPorId(Long id) throws ProductoException {
        return productoRepository.findById(id).orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado"));
    }

    private ProductoEntity consultarProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre).orElse(null);
    }
}
