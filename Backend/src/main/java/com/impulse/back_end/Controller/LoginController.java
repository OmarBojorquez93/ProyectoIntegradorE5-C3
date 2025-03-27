package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.LoginPeticionDTO;
import com.impulse.back_end.Dto.LoginRespuestaDTO;
import com.impulse.back_end.Entity.UsuarioEntity;
import com.impulse.back_end.Entity.UsuarioRole;
import com.impulse.back_end.Service.LoginService;
import com.impulse.back_end.Service.UsuarioService;
import com.impulse.back_end.exception.LoginException;
import com.impulse.back_end.exception.UsuarioException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.PublicRoutes.LOGIN)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioService usuarioService; // Servicio para consultar el usuario

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginRespuestaDTO login(
            @RequestBody @Valid LoginPeticionDTO loginPeticionDTO
    ) throws LoginException {
        // Ejecutar el login
        LoginRespuestaDTO respuesta = loginService.login(loginPeticionDTO);

        try {
            // Obtener el usuario desde la base de datos
            UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(loginPeticionDTO.getEmail());

            // Determinar si el usuario es administrador
            boolean esAdmin = UsuarioRole.fromValue(usuario.getUsuarioRole()) == com.impulse.back_end.Entity.UsuarioRole.ROLE_ADMIN;

            // Agregar el valor de administrador a la respuesta
            respuesta.setEsAdmin(esAdmin);
        } catch (UsuarioException e) {
            throw new LoginException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario no existe o credenciales inválidas.");
        }

        return respuesta;
    }
}

