package com.impulse.back_end.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class ProductoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ReservaEntity.class)
    private List<ReservaEntity> reservas;

    public ProductoEntity(String nombre, String descripcion, BigDecimal precioAlquiler, CategoriaEntity categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAlquiler = precioAlquiler;
        this.categoria = categoria;
    }

    public void addImagen(ImagenEntity imagen) {
        imagenes.add(imagen);
        imagen.setProducto(this);
    }

    public void removeImagen(ImagenEntity imagen) {
        imagenes.remove(imagen);
        imagen.setProducto(null);
    }

    public void addCaracteristica(CaracteristicaEntity caracteristica) {
        caracteristicas.add(caracteristica);
        caracteristica.setProducto(this);
    }

    public void removeCaracteristica(CaracteristicaEntity caracteristica) {
        caracteristicas.remove(caracteristica);
        caracteristica.setProducto(null);
    }
}
