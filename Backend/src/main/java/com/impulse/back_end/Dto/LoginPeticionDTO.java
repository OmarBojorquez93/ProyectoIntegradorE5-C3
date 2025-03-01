package com.impulse.back_end.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginPeticionDTO {

    @NotEmpty(message = "El email es requerido")
    @Email(message = "El email debe ser una direccion de correo electronico valido")
    private String email;

    @NotEmpty(message = "El password es requerido")
    private String password;

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
