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
<<<<<<< Updated upstream
import com.impulse.back_end.Repository.ProductoRepository;
=======
>>>>>>> Stashed changes
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
=======
    public ProductoRespuestaDTO consultarProductoPorId(Long id) throws ProductoException {
        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty())
            throw new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado");

        return ProductoMapper.mapProductoRespuestaDTO(productoOptional.get());
>>>>>>> Stashed changes
    }
<<<<<<< Updated upstream

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
=======
    public List<ProductoRespuestaDTO> consultarProductos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .collect(Collectors.toList());
    }

    // ✅ HU #31: Verificar disponibilidad de fechas
    public boolean verificarDisponibilidadProducto(Long productoId, LocalDate desde, LocalDate hasta) throws ProductoException {
        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado"));

        List<LocalDate> fechasNoDisponibles = producto.getFechasNoDisponibles();

        for (LocalDate fecha = desde; !fecha.isAfter(hasta); fecha = fecha.plusDays(1)) {
            if (fechasNoDisponibles.contains(fecha)) {
                return false;
            }
        }
        return true;
    }
>>>>>>> Stashed changes
}
