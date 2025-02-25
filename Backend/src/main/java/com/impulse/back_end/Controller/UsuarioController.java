package com.impulse.back_end.Controller;

import com.impulse.back_end.Dto.UsuarioPeticionDTO;
import com.impulse.back_end.Dto.UsuarioRespuestaDTO;
import com.impulse.back_end.Service.UsuarioService;
import com.impulse.back_end.exception.UsuarioException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRespuestaDTO registrarUsuario(
            @RequestBody @Valid UsuarioPeticionDTO usuarioPeticionDTO
    ) throws UsuarioException {
        return usuarioService.registrarUsuario(usuarioPeticionDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioRespuestaDTO consultarUsuarioPorId(
            @PathVariable("id") Long id
    ) throws UsuarioException {
        return usuarioService.consultarUsuarioPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioRespuestaDTO> consultarUsuarios() {
        return usuarioService.consultarUsuarios();
    }
}
