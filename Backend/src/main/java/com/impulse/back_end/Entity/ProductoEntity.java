// ProductoEntity.java
// ===========================
package com.impulse.back_end.Entity;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import java.time.LocalDate;
=======

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String marca;

    private String ubicacion;

<<<<<<< Updated upstream
    @Column(name = "fecha_disponible")
    private LocalDate fechaDisponible;
=======
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

    // ✅ HU #30: Lista de fechas no disponibles para el producto
    @ElementCollection
    @CollectionTable(name = "fechas_no_disponibles", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "fecha")
    private List<LocalDate> fechasNoDisponibles = new ArrayList<>();

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
>>>>>>> Stashed changes

    // Getters y setters
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(LocalDate fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }
<<<<<<< Updated upstream
}
=======

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    // ✅ Getters y setters de fechas no disponibles
    public List<LocalDate> getFechasNoDisponibles() {
        return fechasNoDisponibles;
    }

    public void setFechasNoDisponibles(List<LocalDate> fechasNoDisponibles) {
        this.fechasNoDisponibles = fechasNoDisponibles;
    }
}
>>>>>>> Stashed changes
