package com.impulse.back_end.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Service.ProductoAdminService;
import com.impulse.back_end.exception.ProductoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.AdminRoutes.PRODUCTO)
public class ProductoAdminController {

    @Autowired
    private ProductoAdminService productoAdminService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoRespuestaDTO registrarProducto(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @RequestParam(value = "peticion", required = true) String peticion,
            @RequestParam(value = "imagen", required = true) MultipartFile imagen
    ) throws ProductoException, JsonProcessingException {
        return productoAdminService.registrarProducto(sessionId, peticion, imagen);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductoRespuestaDTO modificarProductoPorId(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id,
            @RequestParam(value = "peticion", required = true) String peticion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) throws ProductoException, JsonProcessingException {
        return productoAdminService.modificarProductoPorId(sessionId, id, peticion, imagen);
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
}