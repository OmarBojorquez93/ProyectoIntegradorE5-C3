// ProductoPublicController.java
// ===========================
package com.impulse.back_end.Controller;

import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Service.ProductoPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoPublicController {

    @Autowired
    private ProductoPublicService productoPublicService;

<<<<<<< Updated upstream
    @GetMapping("/buscar-texto")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoRespuestaDTO> buscarPorTexto(@RequestParam("texto") String texto) {
        return productoPublicService.buscarPorTexto(texto);
    }

    @GetMapping("/buscar-fecha")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoRespuestaDTO> buscarPorFecha(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return productoPublicService.buscarPorFecha(desde, hasta);
    }
=======
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoRespuestaDTO> buscarPorTextoYFecha(
            @RequestParam("texto") String texto,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return productoPublicService.buscarPorTextoYFecha(texto, desde, hasta);
    }
>>>>>>> Stashed changes
}
