package com.impulse.back_end.Service;

import com.impulse.back_end.Entity.ImagenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ImagenService {

    private final Logger logger = LoggerFactory.getLogger(ImagenService.class);

    public ImagenEntity subirImagen(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                return null;
            }

            // TODO cambiar al servicio donde se vayan a almacenar las imagenes
            File uploadedFile = new File(System.getProperty("user.dir") + "/" + archivo.getOriginalFilename());
            archivo.transferTo(uploadedFile);

            return new ImagenEntity(uploadedFile.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Error al subir el archivo {}", archivo.getOriginalFilename(), e);
            return null;
        }
    }

    public void borrarImagen(String ruta) {
        try {
            // TODO cambiar al servicio donde se vayan a almacenar las imagenes
            new File(ruta).delete();
        } catch (Exception e) {
            logger.error("Error al borrar el archivo {}", ruta, e);
        }
    }
}
