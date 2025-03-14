package com.impulse.back_end.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "imagenes")
public class ImagenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;

    @Column(name = "ruta", nullable = false, length = 350)
    private String ruta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    public ImagenEntity(String ruta) {
        this.ruta = ruta;
    }

    public ImagenEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
