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
@AllArgsConstructor
@NoArgsConstructor
public class ReservaPeticionDTO {

    private Long idProducto;

    private LocalDate fechaDesde;

    private LocalDate fechaHasta;
}
