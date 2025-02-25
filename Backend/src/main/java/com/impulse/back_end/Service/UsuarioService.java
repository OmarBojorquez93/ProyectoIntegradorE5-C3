package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.UsuarioPeticionDTO;
import com.impulse.back_end.Dto.UsuarioRespuestaDTO;
import com.impulse.back_end.Entity.UsuarioEntity;
import com.impulse.back_end.Entity.UsuarioRole;
import com.impulse.back_end.Repository.UsuarioRepository;
import com.impulse.back_end.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsernameNotFoundException("Usuario inexistente: " + username);
        }
    }

    public UsuarioRespuestaDTO registrarUsuario(UsuarioPeticionDTO usuarioPeticionDTO) throws UsuarioException {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(usuarioPeticionDTO.getEmail());
        if (usuario.isPresent()) {
            throw new UsuarioException(HttpStatus.CONFLICT, "usuario_invalido", "El usuario con email " + usuarioPeticionDTO.getEmail() +" ya existe");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.save(
                new UsuarioEntity(
                        usuarioPeticionDTO.getNombre(),
                        usuarioPeticionDTO.getApellido(),
                        usuarioPeticionDTO.getEmail(),
                        bCryptPasswordEncoder.encode(usuarioPeticionDTO.getPassword()),
                        UsuarioRole.valueOf(usuarioPeticionDTO.getRole())
                )
        );

        return new UsuarioRespuestaDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getEmail(),
                usuarioEntity.getUsuarioRole().name()
        );
    }

    public UsuarioRespuestaDTO consultarUsuarioPorId(Long id) throws UsuarioException {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (!usuarioEntity.isPresent()) {
            throw new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con id " + id +" no existe");
        }

        return new UsuarioRespuestaDTO(
                usuarioEntity.get().getId(),
                usuarioEntity.get().getNombre(),
                usuarioEntity.get().getApellido(),
                usuarioEntity.get().getEmail(),
                usuarioEntity.get().getUsuarioRole().name()
        );
    }

    public List<UsuarioRespuestaDTO> consultarUsuarios() {
        return usuarioRepository.findAll().stream().map( (u) -> {
            return new UsuarioRespuestaDTO(
                    u.getId(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getEmail(),
                    u.getUsuarioRole().name()
            );
        }).collect(Collectors.toList());
    }
}
