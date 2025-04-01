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
            if (archivo.isEmpty()) {
                return null;
            }
            
            Optional<ProductoEntity> productoOpt = productoRepository.findById(productoId);
            if (productoOpt.isEmpty()) {
                logger.error("Producto con ID {} no encontrado", productoId);
                return null;
            }
            
            ProductoEntity producto = productoOpt.get();
            
            // Si el producto ya tiene una imagen, eliminar la anterior
            if (producto.getImagen() != null) {
                borrarImagen(producto.getImagen().getRuta());
                imagenRepository.delete(producto.getImagen());
            }
            
            // Generar un nombre único para el archivo
            String fileName = UUID.randomUUID().toString() + "-" + archivo.getOriginalFilename();
            InputStream inputStream = archivo.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(archivo.getSize());
            
            // Subir archivo a S3
            s3Client.putObject(bucketName, fileName, inputStream, metadata);
            
            // Obtener URL pública del archivo
            String fileUrl = s3Client.getUrl(bucketName, fileName).toString();
            logger.info("FileUrl {}", fileUrl);
            // Guardar la nueva imagen en la BD
            ImagenEntity nuevaImagen = new ImagenEntity(fileUrl);
            nuevaImagen.setProducto(producto);
            imagenRepository.save(nuevaImagen);
            
            // Asociar la imagen al producto y guardar el producto
            producto.setImagen(nuevaImagen);
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
