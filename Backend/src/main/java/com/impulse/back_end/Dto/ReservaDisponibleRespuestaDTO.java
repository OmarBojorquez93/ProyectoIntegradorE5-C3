package com.impulse.back_end.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReservaDisponibleRespuestaDTO {

    private String nombreProducto;

    private String descripcionProducto;

    private LocalDate desde;

    private LocalDate hasta;

    private boolean disponible;

}
