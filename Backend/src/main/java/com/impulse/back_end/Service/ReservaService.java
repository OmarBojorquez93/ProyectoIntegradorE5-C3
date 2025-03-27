package com.impulse.back_end.Service;

import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Entity.ReservaEntity;
import com.impulse.back_end.Entity.SessionEntity;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.Repository.ReservaRepository;
import com.impulse.back_end.Repository.SessionRepository;
import com.impulse.back_end.exception.ProductoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final ProductoRepository productoRepository;

    private final SessionRepository sessionRepository;

    public ReservaEntity guardarReserva(final String sessionId, final Long idProducto, final LocalDate fechaDesde, final LocalDate fechaHasta) throws ProductoException {
        final String emailUsuario = this.sessionRepository.findBySession(sessionId).map(SessionEntity::getEmail).orElse(null);
        return this.productoRepository.findById(idProducto).map(producto -> {
            final ReservaEntity temp = ReservaEntity.builder().producto(producto).emailUsuario(emailUsuario).desde(fechaDesde).hasta(fechaHasta).build();
            return this.reservaRepository.save(temp);
        }).orElseThrow(() -> new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no existe"));
    }
}
