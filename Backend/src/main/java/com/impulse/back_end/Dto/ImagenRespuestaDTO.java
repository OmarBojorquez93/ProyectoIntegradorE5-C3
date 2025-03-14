package com.impulse.back_end.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id", "ruta"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagenRespuestaDTO {

    private Long id;

    private String ruta;

    public ImagenRespuestaDTO(Long id, String ruta) {
        this.id = id;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
