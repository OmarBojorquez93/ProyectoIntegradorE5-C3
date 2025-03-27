package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.ReservaPeticionDTO;
import com.impulse.back_end.Entity.ReservaEntity;
import com.impulse.back_end.Service.ReservaService;
import com.impulse.back_end.exception.ProductoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Constants.PublicRoutes.RESERVA)
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaEntity> guardarReserva(@RequestHeader(Constants.Headers.SESSION_ID) String sessionId, @RequestBody ReservaPeticionDTO reserva) throws ProductoException {
        final ReservaEntity saved = this.reservaService.guardarReserva(sessionId, reserva.getIdProducto(), reserva.getFechaDesde(), reserva.getFechaHasta())    ;
        return ResponseEntity.ok().body(saved);
    }
}
