package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.CategoriaPeticionDTO;
import com.impulse.back_end.Dto.CategoriaRespuestaDTO;
import com.impulse.back_end.Service.CategoriaService;
import com.impulse.back_end.exception.CategoriaException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.AdminRoutes.CATEGORIA)
public class CategoriaAdminController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaRespuestaDTO registrarCategoria(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @RequestBody @Valid CategoriaPeticionDTO categoriaPeticionDTO
    ) throws CategoriaException {
        return categoriaService.registrarCategoria(sessionId, categoriaPeticionDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaRespuestaDTO modificarCategoriaPorId(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id,
            @RequestBody @Valid CategoriaPeticionDTO categoriaPeticionDTO
    ) throws CategoriaException {
        return categoriaService.modificarCategoriaPorId(sessionId, id, categoriaPeticionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoriaPorId(
            @RequestHeader(Constants.Headers.SESSION_ID) String sessionId,
            @PathVariable("id") Long id
    ) throws CategoriaException {
        categoriaService.eliminarCategoriaPorId(
                sessionId,
                id
        );
    }
}