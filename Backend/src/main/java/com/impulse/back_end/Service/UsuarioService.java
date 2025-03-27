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
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente: " + username));
    }

    public UsuarioRespuestaDTO registrarUsuario(UsuarioPeticionDTO usuarioPeticionDTO) throws UsuarioException {
        if (usuarioRepository.existsByEmail(usuarioPeticionDTO.getEmail())) {
            throw new UsuarioException(HttpStatus.CONFLICT, "usuario_invalido", "El usuario con email " + usuarioPeticionDTO.getEmail() + " ya existe");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.save(
                new UsuarioEntity(
                        usuarioPeticionDTO.getNombre().trim(),
                        usuarioPeticionDTO.getApellido().trim(),
                        usuarioPeticionDTO.getEmail().trim(),
                        bCryptPasswordEncoder.encode(usuarioPeticionDTO.getPassword().trim()),
                        usuarioPeticionDTO.getAdmin() ? UsuarioRole.ROLE_ADMIN : UsuarioRole.ROLE_USER
                )
        );

        return convertirAUsuarioRespuestaDTO(usuarioEntity);
    }

    public UsuarioRespuestaDTO consultarUsuarioPorId(Long id) throws UsuarioException {
        UsuarioEntity usuarioEntity = obtenerUsuarioPorId(id);
        return convertirAUsuarioRespuestaDTO(usuarioEntity);
    }

    public boolean existeUsuarioPorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public List<UsuarioRespuestaDTO> consultarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAUsuarioRespuestaDTO)
                .collect(Collectors.toList());
    }

    public UsuarioEntity obtenerUsuarioPorEmail(String email) throws UsuarioException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con email " + email + " no existe"));
    }

    @Transactional
    public String asignarAdmin(Long id) throws UsuarioException {
        UsuarioEntity usuario = obtenerUsuarioPorId(id);

        if (usuario.getUsuarioRole() == UsuarioRole.ROLE_ADMIN) {
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "usuario_ya_admin", "El usuario ya es administrador");
        }

        usuario.setUsuarioRole(UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);
        return "El usuario ahora es administrador";
    }

    @Transactional
    public String removerAdmin(Long id) throws UsuarioException {
        UsuarioEntity usuario = obtenerUsuarioPorId(id);

        if (usuario.getUsuarioRole() == UsuarioRole.ROLE_USER) {
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "usuario_no_admin", "El usuario no es administrador");
        }

        usuario.setUsuarioRole(UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        return "El usuario ya no es administrador";
    }

    // 🔹 Método privado para reutilizar la lógica de obtención de usuario por ID
    private UsuarioEntity obtenerUsuarioPorId(Long id) throws UsuarioException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(HttpStatus.NOT_FOUND, "usuario_no_existe", "El usuario con id " + id + " no existe"));
    }

    // 🔹 Método privado para convertir UsuarioEntity a UsuarioRespuestaDTO
    private UsuarioRespuestaDTO convertirAUsuarioRespuestaDTO(UsuarioEntity usuario) {
        return new UsuarioRespuestaDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.isAdmin()
        );
    }
}
