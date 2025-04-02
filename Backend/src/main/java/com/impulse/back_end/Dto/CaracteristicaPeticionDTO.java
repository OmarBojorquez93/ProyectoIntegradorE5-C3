package com.impulse.back_end.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CaracteristicaPeticionDTO {

    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 1, max = 150, message = "El nombre no debe ser mayor a 150 caracteres")
    private String nombre;

    @NotEmpty(message = "La descripcion es requerida")
    @Size(min = 1, max = 1000, message = "La descripcion no debe ser mayor a 1000 caracteres")
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
