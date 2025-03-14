package com.impulse.back_end.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductoPeticionDTO {

    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre no debe ser mayor a 100 caracteres")
    private String nombre;

    @NotEmpty(message = "La descripcion es requerida")
    private String descripcion;

    @NotNull(message = "El precio de alquiler es requerido")
    @Positive(message = "El precio de alquiler debe ser mayor a cero")
    @JsonProperty("precio_alquiler")
    private BigDecimal precioAlquiler;

    @NotEmpty(message = "Se requiere especificar al menos una caracteristica del producto")
    @Valid
    private List<CaracteristicaPeticionDTO> caracteristicas;

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

    public BigDecimal getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(BigDecimal precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public List<CaracteristicaPeticionDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaPeticionDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
