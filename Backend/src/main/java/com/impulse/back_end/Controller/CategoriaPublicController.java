package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.CategoriaRespuestaDTO;
import com.impulse.back_end.Service.CategoriaService;
import com.impulse.back_end.exception.CategoriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.PublicRoutes.CATEGORIA)
public class CategoriaPublicController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaRespuestaDTO consultarCategoriaPorId(
            @PathVariable("id") Long id
    ) throws CategoriaException {
        return categoriaService.consultarCategoriaPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaRespuestaDTO> consultarCategorias(
    ) throws CategoriaException {
        return categoriaService.consultarCategorias();
    }
}