package com.impulse.back_end.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impulse.back_end.Dto.*;
import com.impulse.back_end.Entity.CaracteristicaEntity;
import com.impulse.back_end.Entity.CategoriaEntity;
import com.impulse.back_end.Entity.ImagenEntity;
import com.impulse.back_end.Entity.ProductoEntity;

import java.util.stream.Collectors;

public class ProductoMapper {

    static final ObjectMapper mapper = new ObjectMapper();

    public static ProductoPeticionDTO mapProductoPeticionDTO(String peticion) throws JsonProcessingException {
        return ProductoMapper.mapper.readValue(peticion, ProductoPeticionDTO.class);
    }

    public static ProductoRespuestaDTO mapProductoRespuestaDTO(ProductoEntity productoEntity) {
        return new ProductoRespuestaDTO(
                productoEntity.getId(),
                productoEntity.getNombre(),
                productoEntity.getDescripcion(),
                productoEntity.getPrecioAlquiler(),
                productoEntity.getCategoria().getNombre(),
                productoEntity.getCaracteristicas().stream().map(caracteristicaEntity -> {
                    return new CaracteristicaRespuestaDTO(
                            caracteristicaEntity.getId(),
                            caracteristicaEntity.getNombre(),
                            caracteristicaEntity.getDescripcion()
                    );
                }).collect(Collectors.toList()),
                productoEntity.getImagenes().stream().map(imagenEntity -> {
                    return new ImagenRespuestaDTO(
                            imagenEntity.getId(),
                            imagenEntity.getRuta()
                    );
                }).collect(Collectors.toList())
        );
    }

    public static ProductoEntity mapNewProductoEntity(
            ProductoPeticionDTO productoPeticionDTO,
            ImagenEntity imagenEntity,
            CategoriaEntity categoriaEntity
    ) {
        ProductoEntity productoEntity = new ProductoEntity(
                productoPeticionDTO.getNombre(),
                productoPeticionDTO.getDescripcion(),
                productoPeticionDTO.getPrecioAlquiler(),
                categoriaEntity
        );

        for (CaracteristicaPeticionDTO caracteristica: productoPeticionDTO.getCaracteristicas()) {
            productoEntity.addCaracteristica(new CaracteristicaEntity(
                    caracteristica.getNombre(),
                    caracteristica.getDescripcion()
            ));
        }

        productoEntity.addImagen(imagenEntity);

        return productoEntity;
    }
}
