package com.impulse.back_end.mapper;

import com.impulse.back_end.Dto.CaracteristicaRespuestaDTO;
import com.impulse.back_end.Dto.ImagenRespuestaDTO;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.ProductoEntity;

import java.util.stream.Collectors;

public class ProductoMapper {

    public static ProductoRespuestaDTO mapProductoRespuestaDTO(ProductoEntity productoEntity) {
        return new ProductoRespuestaDTO(
                productoEntity.getId(),
                productoEntity.getNombre(),
                productoEntity.getDescripcion(),
                productoEntity.getPrecioAlquiler(),
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
}
