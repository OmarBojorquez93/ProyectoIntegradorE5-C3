package com.impulse.back_end.Dto;

import jakarta.validation.constraints.*;

public class UsuarioPeticionDTO {

    @NotNull(message = "El nombre es requerido")
    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "El apellido es requerido")
    @NotEmpty(message = "El apellido es requerido")
    private String apellido;

    @NotNull(message = "El email es requerido")
    @NotEmpty(message = "El email es requerido")
    @Email(message = "El email debe ser una direccion de correo electronico valido")
    private String email;

    @NotNull(message = "El password es requerido")
    @Size(min = 6, message = "El password debe tener como minimo 6 caracteres")
    private String password;

    @NotNull(message = "Los roles permitidos son ROLE_USER, ROLE_ADMIN")
    @Pattern(regexp = "ROLE_USER|ROLE_ADMIN")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
