package com.impulse.back_end.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder(value = {"id", "nombre", "descripcion", "precioAlquiler", "categoria", "caracteristicas", "imagenes"})
public class ProductoRespuestaDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    @JsonProperty("precio_alquiler")
    private BigDecimal precioAlquiler;

    private String categoria;

    private List<CaracteristicaRespuestaDTO> caracteristicas;

    private List<ImagenRespuestaDTO> imagenes;


    public ProductoRespuestaDTO(Long id, String nombre, String descripcion, BigDecimal precioAlquiler, String categoria, List<CaracteristicaRespuestaDTO> caracteristicas, List<ImagenRespuestaDTO> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAlquiler = precioAlquiler;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.imagenes = imagenes;
    }

    public ProductoRespuestaDTO() {
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

    public List<ImagenRespuestaDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenRespuestaDTO> imagenes) {
        this.imagenes = imagenes;
    }

    public List<CaracteristicaRespuestaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaRespuestaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
