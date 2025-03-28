package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Dto.ReservaDisponibleRespuestaDTO;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Entity.ReservaEntity;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.Repository.ReservaRepository;
import com.impulse.back_end.exception.ProductoException;
import com.impulse.back_end.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.impulse.back_end.mapper.ProductoMapper.mapProductoRespuestaDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoPublicService {

    private final ProductoRepository productoRepository;

    private final ReservaRepository reservaRepository;

    public ProductoRespuestaDTO consultarProductoPorId(Long id) throws ProductoException {
        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);

        if (productoOptional.isEmpty())
            throw new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado");

        return mapProductoRespuestaDTO(productoOptional.get());
    }

    public List<ProductoRespuestaDTO> consultarProductos() throws ProductoException {
        return productoRepository.findAll().stream().map(ProductoMapper::mapProductoRespuestaDTO).collect(Collectors.toList());
    }

    public List<ProductoRespuestaDTO> buscarProductoPorTextoYFechas(final String palabraClave, final LocalDate fechaDesde, final LocalDate fechaHasta) {
        final List<ProductoEntity> productosReservados = this.reservaRepository.findAllByDesdeAfterAndHastaBefore(fechaDesde, fechaHasta).stream().distinct().map(ReservaEntity::getProducto).toList();
        return this.productoRepository.findAll().stream()
                .filter(producto -> !productosReservados.contains(producto))
                .filter(producto -> producto.getNombre().contains(palabraClave) || producto.getDescripcion().contains(palabraClave))
                .map(ProductoMapper::mapProductoRespuestaDTO)
                .toList();
    }

    public ReservaDisponibleRespuestaDTO productoDisponibleParaReserva(final Long idProducto, final LocalDate fechaDesde, final LocalDate fechaHasta) throws ProductoException {
        return productoRepository.findById(idProducto)
                .map(producto -> {
                    final Optional<ReservaEntity> reserva = this.reservaRepository.findOneByProductoAndDesdeAfterAndHastaBefore(producto, fechaDesde, fechaHasta);
                    return ReservaDisponibleRespuestaDTO.builder().nombreProducto(producto.getNombre()).descripcionProducto(producto.getDescripcion()).disponible(reserva.isEmpty()).build();
                })
                .orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado"));
    }

    public List<LocalDate> fechasNoDisponiblesPorProducto(final Long idProducto) throws ProductoException {
        final List<LocalDate> fechasNoDisponibles = new ArrayList<>();
        final ProductoEntity producto = this.productoRepository.findById(idProducto).orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado"));

        this.reservaRepository.findAllByProductoAndDesdeAfter(producto, LocalDate.now()).forEach(reserva -> fechasNoDisponibles.addAll(obtenerFechasEntreRango(reserva.getDesde(), reserva.getHasta())));
        return fechasNoDisponibles;
    }

    private Set<LocalDate> obtenerFechasEntreRango(final LocalDate desde, final LocalDate hasta) {
        return Stream.iterate(desde, fecha -> fecha.plusDays(1)).limit(ChronoUnit.DAYS.between(desde, hasta)).collect(Collectors.toSet());
    }
}