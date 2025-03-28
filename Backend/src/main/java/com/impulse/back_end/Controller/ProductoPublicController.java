package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Dto.ReservaDisponibleRespuestaDTO;
import com.impulse.back_end.Service.ProductoPublicService;
import com.impulse.back_end.exception.ProductoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = Constants.PublicRoutes.PRODUCTO)
public class ProductoPublicController {

    @Autowired
    private ProductoPublicService productoPublicService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoRespuestaDTO consultarProductoPorId(
            @PathVariable("id") Long id
    ) throws ProductoException {
        return productoPublicService.consultarProductoPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoRespuestaDTO> consultarProductos(
    ) throws ProductoException {
        return productoPublicService.consultarProductos();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoRespuestaDTO>> consultarPorPalabraClave(@RequestParam("texto") final String texto,
                                                                               @RequestParam("fechaDesde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaDesde,
                                                                               @RequestParam("fechaHasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaHasta) {
        return ResponseEntity.ok(productoPublicService.buscarProductoPorTextoYFechas(texto, fechaDesde, fechaHasta));
    }

    @GetMapping("/reserva-disponible")
    public ResponseEntity<ReservaDisponibleRespuestaDTO> productoDisponibleParaReserva(@RequestParam("idProducto") final Long idProducto,
                                                                                       @RequestParam("fechaDesde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaDesde,
                                                                                       @RequestParam("fechaHasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaHasta) throws ProductoException {
        return ResponseEntity.ok(this.productoPublicService.productoDisponibleParaReserva(idProducto, fechaDesde, fechaHasta));
    }

    @GetMapping("/fechas-no-disponibles")
    public ResponseEntity<List<LocalDate>> obtenerFechasNoDisponibles(@RequestParam("idProducto") final Long idProducto) throws ProductoException {
        return ResponseEntity.ok(this.productoPublicService.fechasNoDisponiblesPorProducto(idProducto));
    }

}