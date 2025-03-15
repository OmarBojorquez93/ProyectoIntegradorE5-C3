package com.impulse.back_end.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio_alquiler", nullable = false)
    private BigDecimal precioAlquiler;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @OneToMany(
            mappedBy = "producto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ImagenEntity> imagenes = new ArrayList<>();

    @OneToMany(
            mappedBy = "producto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CaracteristicaEntity> caracteristicas = new ArrayList<>();

    public ProductoEntity(String nombre, String descripcion, BigDecimal precioAlquiler, CategoriaEntity categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAlquiler = precioAlquiler;
        this.categoria = categoria;
    }

    public ProductoEntity() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioAlquiler(BigDecimal precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void addImagen(ImagenEntity imagen) {
        imagenes.add(imagen);
        imagen.setProducto(this);
    }

    public void removeImagen(ImagenEntity imagen) {
        imagenes.remove(imagen);
        imagen.setProducto(null);
    }

    public List<ImagenEntity> getImagenes() {
        return imagenes;
    }

    public void addCaracteristica(CaracteristicaEntity caracteristica) {
        caracteristicas.add(caracteristica);
        caracteristica.setProducto(this);
    }

    public void removeCaracteristica(CaracteristicaEntity caracteristica) {
        caracteristicas.remove(caracteristica);
        caracteristica.setProducto(null);
    }

    public List<CaracteristicaEntity> getCaracteristicas() {
        return caracteristicas;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}
