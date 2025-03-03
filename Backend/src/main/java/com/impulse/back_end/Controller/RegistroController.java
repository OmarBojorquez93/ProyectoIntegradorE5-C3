package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.UsuarioPeticionDTO;
import com.impulse.back_end.Dto.UsuarioRespuestaDTO;
import com.impulse.back_end.Service.UsuarioService;
import com.impulse.back_end.exception.UsuarioException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.PublicRoutes.REGISTRO)
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRespuestaDTO registrarUsuario(
            @RequestBody @Valid UsuarioPeticionDTO usuarioPeticionDTO
    ) throws UsuarioException {
        return usuarioService.registrarUsuario(usuarioPeticionDTO);
    }
}
