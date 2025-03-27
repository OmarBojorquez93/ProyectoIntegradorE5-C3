package com.impulse.back_end.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    
    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "usuario_role") // Asegúrate de que el nombre de la columna en la BD sea el correcto
    private int usuarioRole;  // Cambiar a int en lugar de UsuarioRole

    public UsuarioEntity(String nombre, String apellido, String email, String password, int usuarioRole) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    public UsuarioEntity() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir el valor entero de usuarioRole al enum
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(UsuarioRole.fromValue(usuarioRole).name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getIdUsuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getUsuarioRole() {
        return usuarioRole;
    }

    public Boolean isAdmin() {
        return UsuarioRole.fromValue(usuarioRole) == UsuarioRole.ROLE_ADMIN;
    }

    public String getAvatar() {
        return Arrays.stream(getNombre().split(" ")).toList().stream().map(n -> n.charAt(0) + "").collect(Collectors.joining()) +
                Arrays.stream(getApellido().split(" ")).toList().stream().map(n -> n.charAt(0) + "").collect(Collectors.joining());
    }

    public void setUsuarioRole(int usuarioRole) {
        this.usuarioRole = usuarioRole;
    }

    public void asignarAdmin() {
        this.usuarioRole = UsuarioRole.ROLE_ADMIN.getValue();
    }

    public void removerAdmin() {
        this.usuarioRole = UsuarioRole.ROLE_USER.getValue();
    }
}
