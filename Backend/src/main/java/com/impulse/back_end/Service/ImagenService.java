package com.impulse.back_end.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.impulse.back_end.Entity.ImagenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class ImagenService {

    private final Logger logger = LoggerFactory.getLogger(ImagenService.class);
    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public ImagenService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public ImagenEntity subirImagen(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                return null;
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
            
            return new ImagenEntity(fileUrl);
        } catch (Exception e) {
            logger.error("Error al subir el archivo {}", archivo.getOriginalFilename(), e);
            return null;
        }
    }

    public void borrarImagen(String ruta) {
        try {
            String fileName = ruta.substring(ruta.lastIndexOf("/") + 1);
            s3Client.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            logger.error("Error al borrar el archivo {}", ruta, e);
        }
    }
}
