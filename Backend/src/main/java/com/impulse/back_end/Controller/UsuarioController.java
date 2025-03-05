package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.UsuarioRespuestaDTO;
import com.impulse.back_end.Service.UsuarioService;
import com.impulse.back_end.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.AdminRoutes.USUARIO)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
    @PutMapping("/{id}/asignar-admin")
    @ResponseStatus(HttpStatus.OK)
    public String asignarAdmin(@PathVariable("id") Long id) throws UsuarioException {
        return usuarioService.asignarAdmin(id);
    }

    @PutMapping("/{id}/remover-admin")
    @ResponseStatus(HttpStatus.OK)
    public String removerAdmin(@PathVariable("id") Long id) throws UsuarioException {
        return usuarioService.removerAdmin(id);
    }

}