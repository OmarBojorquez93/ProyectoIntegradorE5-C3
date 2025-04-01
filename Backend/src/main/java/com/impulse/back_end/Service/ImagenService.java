package com.impulse.back_end.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.impulse.back_end.Entity.ImagenEntity;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.ImagenRepository;
import com.impulse.back_end.Repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagenService {

    private final Logger logger = LoggerFactory.getLogger(ImagenService.class);
    private final AmazonS3 s3Client;
    private final ImagenRepository imagenRepository;
    private final ProductoRepository productoRepository;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public ImagenService(AmazonS3 s3Client, ImagenRepository imagenRepository, ProductoRepository productoRepository) {
        this.s3Client = s3Client;
        this.imagenRepository = imagenRepository;
        this.productoRepository = productoRepository;
    }

    public ImagenEntity subirImagen(MultipartFile archivo, Long productoId) {
        try {
            logger.info("Iniciando subida de imagen para el producto ID: {}", productoId);

            if (archivo.isEmpty()) {
                logger.error("El archivo está vacío.");
                return null;
            }
            
            logger.info("Buscando producto en la base de datos...");
            Optional<ProductoEntity> productoOpt = productoRepository.findById(productoId);
            if (productoOpt.isEmpty()) {
                logger.error("Producto con ID {} no encontrado", productoId);
                return null;
            }
            
            ProductoEntity producto = productoOpt.get();
            logger.info("Producto encontrado: {} - {}", producto.getId(), producto.getNombre());

            // Si el producto ya tiene una imagen, eliminar la anterior
            if (producto.getImagen() != null) {
                logger.info("Producto ya tiene una imagen, se procederá a eliminarla.");
                borrarImagen(producto.getImagen().getRuta());
                imagenRepository.delete(producto.getImagen());
            }
            
            // Generar un nombre único para el archivo
            String fileName = UUID.randomUUID().toString() + "-" + archivo.getOriginalFilename();
            logger.info("Nombre de archivo generado: {}", fileName);

            InputStream inputStream = archivo.getInputStream();
            logger.info("InputStream obtenido con éxito.");

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(archivo.getSize());
            logger.info("Metadata de la imagen creada. Tamaño: {}", archivo.getSize());

            // Subir archivo a S3
            logger.info("Subiendo imagen a S3 en el bucket: {}", bucketName);
            s3Client.putObject(bucketName, fileName, inputStream, metadata);
            logger.info("Imagen subida correctamente.");

            // Obtener URL pública del archivo
            String fileUrl = s3Client.getUrl(bucketName, fileName).toString();
            logger.info("Imagen subida con éxito. URL: {}", fileUrl);

            // Guardar la nueva imagen en la BD
            ImagenEntity nuevaImagen = new ImagenEntity(fileUrl);
            nuevaImagen.setProducto(producto);
            imagenRepository.save(nuevaImagen);
            logger.info("Imagen guardada en la base de datos.");
            
            // Asociar la imagen al producto y guardar el producto
            producto.setImagen(nuevaImagen);
            logger.info("Asociando imagen a producto {} con URL: {}", producto.getId(), fileUrl);
            productoRepository.save(producto);

            return nuevaImagen;
        } catch (Exception e) {
            logger.error("Error al subir la imagen para el producto con ID {}", productoId, e);
            return null;
        }
    }

    public void borrarImagen(String ruta) {
        try {
            if (ruta == null || ruta.isEmpty()) {
                return;
            }
            
            String fileName = ruta.substring(ruta.lastIndexOf("/") + 1);
            s3Client.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            logger.error("Error al borrar la imagen {}", ruta, e);
        }
    }
}
