package com.impulse.back_end.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRespuestaDTO {

    private ProductoRespuestaDTO producto;

    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

    private String email;
}
