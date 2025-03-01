package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.LoginPeticionDTO;
import com.impulse.back_end.Dto.LoginRespuestaDTO;
import com.impulse.back_end.Entity.UsuarioEntity;
import com.impulse.back_end.Repository.UsuarioRepository;
import com.impulse.back_end.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionService sessionService;


    public LoginRespuestaDTO login(LoginPeticionDTO usuarioPeticionDTO) throws LoginException {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByEmail(usuarioPeticionDTO.getEmail());
        if (usuarioOptional.isEmpty()) {
            throw new LoginException(HttpStatus.CONFLICT, "credenciales_incorrectas", "El correo y/o password son incorrectos");
        }

        UsuarioEntity usuarioEntity = usuarioOptional.get();

        if (!bCryptPasswordEncoder.matches(usuarioPeticionDTO.getPassword().trim(), usuarioEntity.getPassword())) {
            throw new LoginException(HttpStatus.CONFLICT, "credenciales_incorrectas", "El correo y/o password son incorrectos");
        }

        System.out.println("consultar session");
        String session = sessionService.consultarSessionByEmail(usuarioEntity.getEmail());

        System.out.println("consultar session respuesta 0 " + session);

        if (session == null) {
            session = sessionService.crearSession(usuarioEntity.getEmail());
        }

        return new LoginRespuestaDTO(
                session,
                usuarioEntity.getId(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getEmail(),
                usuarioEntity.isAdmin(),
                usuarioEntity.getAvatar()
        );
    }
}
