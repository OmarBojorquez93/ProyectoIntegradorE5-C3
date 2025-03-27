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
import org.springframework.transaction.annotation.Transactional;

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

        UsuarioRole role = UsuarioRole.ROLE_USER;
        if (usuarioPeticionDTO.getAdmin()) {
            role = UsuarioRole.ROLE_ADMIN;
        }

        UsuarioEntity usuarioEntity = usuarioRepository.save(
                new UsuarioEntity(
                        usuarioPeticionDTO.getNombre().trim(),
                        usuarioPeticionDTO.getApellido().trim(),
                        usuarioPeticionDTO.getEmail().trim(),
                        bCryptPasswordEncoder.encode(usuarioPeticionDTO.getPassword().trim()),
                        usuarioPeticionDTO.getRole()
                )
        );

        return new UsuarioRespuestaDTO(
                usuarioEntity.getIdUsuario(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getEmail(),
                usuarioEntity.isAdmin()
        );
    }

    public UsuarioRespuestaDTO consultarUsuarioPorId(Long id) throws UsuarioException {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (!usuarioEntity.isPresent()) {
            throw new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con id " + id +" no existe");
        }

        return new UsuarioRespuestaDTO(
                usuarioEntity.get().getIdUsuario(),
                usuarioEntity.get().getNombre(),
                usuarioEntity.get().getApellido(),
                usuarioEntity.get().getEmail(),
                usuarioEntity.get().isAdmin()
        );
    }

    public List<UsuarioRespuestaDTO> consultarUsuarios() {
        return usuarioRepository.findAll().stream().map( (u) -> {
            return new UsuarioRespuestaDTO(
                    u.getIdUsuario(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getEmail(),
                    u.isAdmin()
            );
        }).collect(Collectors.toList());
    }

    // ===========================
    // 📌 NUEVA FUNCIÓN PARA OBTENER USUARIO POR EMAIL
    // ===========================
    public UsuarioEntity obtenerUsuarioPorEmail(String email) throws UsuarioException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con email " + email + " no existe"));
    }

    // ===========================
    // 📌 FUNCIONES PARA ASIGNAR Y REMOVER ADMINISTRADOR
    // ===========================

    @Transactional
    public String asignarAdmin(Long id) throws UsuarioException {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con id " + id + " no existe");
        }

        UsuarioEntity usuario = usuarioOpt.get();
        if (usuario.getUsuarioRole() == UsuarioRole.ROLE_ADMIN) {
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "usuario_ya_admin", "El usuario ya es administrador");
        }

        usuario.setUsuarioRole(UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);
        return "El usuario ahora es administrador";
    }

    @Transactional
    public String removerAdmin(Long id) throws UsuarioException {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con id " + id + " no existe");
        }

        UsuarioEntity usuario = usuarioOpt.get();
        if (usuario.getUsuarioRole() == UsuarioRole.ROLE_USER) {
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "usuario_no_admin", "El usuario no es administrador");
        }

        usuario.setUsuarioRole(UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        return "El usuario ya no es administrador";
    }
}