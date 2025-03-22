package com.impulse.back_end.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoriaPeticionDTO {

    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre no debe ser mayor a 100 caracteres")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
