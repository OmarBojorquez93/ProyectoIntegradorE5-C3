package com.impulse.back_end.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder(value = {"id", "nombre", "descripcion", "precioAlquiler", "categoria", "caracteristicas", "imagen"})
public class ProductoRespuestaDTO {

    private Long id;
    private String nombre;
    private String descripcion;

    @JsonProperty("precio_alquiler")
    private BigDecimal precioAlquiler;

    private String categoria;
    private List<CaracteristicaRespuestaDTO> caracteristicas;

    private ImagenRespuestaDTO imagen;  // ⚠ Cambiado de List<ImagenRespuestaDTO> a ImagenRespuestaDTO

    public ProductoRespuestaDTO(Long id, String nombre, String descripcion, BigDecimal precioAlquiler, String categoria, List<CaracteristicaRespuestaDTO> caracteristicas, ImagenRespuestaDTO imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAlquiler = precioAlquiler;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
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

    public ImagenRespuestaDTO getImagen() {  // ⚠ Nuevo getter para una sola imagen
        return imagen;
    }

    public void setImagen(ImagenRespuestaDTO imagen) {  // ⚠ Nuevo setter para una sola imagen
        this.imagen = imagen;
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
