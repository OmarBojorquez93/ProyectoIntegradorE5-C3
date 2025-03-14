package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.ImagenRespuestaDTO;
import com.impulse.back_end.Dto.ProductoPeticionDTO;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Service.ProductoAdminService;
import com.impulse.back_end.exception.ProductoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.AdminRoutes.PRODUCTO)
public class ProductoAdminController {

    @Autowired
    private ProductoAdminService productoAdminService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoRespuestaDTO registrarProducto(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @RequestBody @Valid ProductoPeticionDTO productoPeticionDTO
    ) throws ProductoException {
        return productoAdminService.registrarProducto(
                sessionId,
                productoPeticionDTO
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoRespuestaDTO modificarProductoPorId(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductoPeticionDTO productoPeticionDTO
    ) throws ProductoException {
        return productoAdminService.modificarProductoPorId(
                sessionId,
                id,
                productoPeticionDTO
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProductoPorId(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id
    ) throws ProductoException {
        productoAdminService.eliminarProductoPorId(
                sessionId,
                id
        );
    }

    @PostMapping("/{id}/imagen")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ImagenRespuestaDTO> subirImagen(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id,
            @RequestParam("imagenes") List<MultipartFile> imagenes
            ) throws ProductoException {
        return productoAdminService.subirImagen(
                sessionId,
                id,
                imagenes
        );
    }

    @DeleteMapping("/{id}/imagen/{id_imagen}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarImagen(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id,
            @PathVariable("id_imagen") Long imagenId
    ) throws ProductoException {
        productoAdminService.borrarImagen(
                sessionId,
                id,
                imagenId
        );
    }

    @DeleteMapping("/{id}/imagen")
    @ResponseStatus(HttpStatus.CREATED)
    public void borrarImagenes(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id
    ) throws ProductoException {
        productoAdminService.borrarImagenes(
                sessionId,
                id
        );
    }
}