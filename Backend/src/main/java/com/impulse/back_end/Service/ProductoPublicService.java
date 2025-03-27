// ProductoPublicService.java
// ===========================
        package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.mapper.ProductoMapper;
import com.impulse.back_end.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoPublicService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoRespuestaDTO> buscarPorTexto(String texto) {
        return productoRepository
                .findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(texto, texto)
                .stream()
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .collect(Collectors.toList());
    }

    public List<ProductoRespuestaDTO> buscarPorFecha(LocalDate desde, LocalDate hasta) {
        return productoRepository
                .findByFechaDisponibleBetween(desde, hasta)
                .stream()
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .collect(Collectors.toList());
    }
}
