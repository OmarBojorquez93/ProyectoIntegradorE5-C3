// ProductoPublicService.java
// ===========================
<<<<<<< Updated upstream
        package com.impulse.back_end.Service;
=======
package com.impulse.back_end.Service;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
    public List<ProductoRespuestaDTO> buscarPorTexto(String texto) {
        return productoRepository
                .findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(texto, texto)
=======
    public List<ProductoRespuestaDTO> buscarPorTextoYFecha(String texto, LocalDate desde, LocalDate hasta) {
        return productoRepository
                .findByFechaDisponibleBetweenAndNombreContainingIgnoreCaseOrFechaDisponibleBetweenAndDescripcionContainingIgnoreCase(
                        desde, hasta, texto, desde, hasta, texto
                )
>>>>>>> Stashed changes
                .stream()
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .collect(Collectors.toList());
    }
<<<<<<< Updated upstream

    public List<ProductoRespuestaDTO> buscarPorFecha(LocalDate desde, LocalDate hasta) {
        return productoRepository
                .findByFechaDisponibleBetween(desde, hasta)
                .stream()
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .collect(Collectors.toList());
    }
=======
>>>>>>> Stashed changes
}
