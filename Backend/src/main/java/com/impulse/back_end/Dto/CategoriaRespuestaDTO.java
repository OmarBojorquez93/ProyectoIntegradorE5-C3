package com.impulse.back_end.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id", "nombre"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaRespuestaDTO {

    private Long id;

    private String nombre;

    public CategoriaRespuestaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
