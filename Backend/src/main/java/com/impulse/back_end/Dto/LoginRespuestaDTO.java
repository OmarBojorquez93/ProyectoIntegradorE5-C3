package com.impulse.back_end.Dto;

public class LoginRespuestaDTO {

    private String session;
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Boolean admin;
    private String avatar;
    private boolean esAdmin; // Nuevo campo para indicar si el usuario es administrador

    public LoginRespuestaDTO(String sessionId, Long id, String nombre, String apellido, String email, Boolean admin, String avatar) {
        this.session = sessionId;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.admin = admin;
        this.avatar = avatar;
        this.esAdmin = admin; // Inicializa esAdmin con el valor de admin
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
        this.esAdmin = admin; // También actualiza esAdmin al modificar admin
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // Nuevo Getter y Setter para esAdmin
    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
        this.admin = esAdmin; // También actualiza admin para mantener consistencia
    }
}
