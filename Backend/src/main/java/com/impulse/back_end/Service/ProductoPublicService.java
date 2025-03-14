package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.ProductoRespuestaDTO;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.exception.ProductoException;
import com.impulse.back_end.mapper.ProductoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.impulse.back_end.mapper.ProductoMapper.mapProductoRespuestaDTO;

@Service
public class ProductoPublicService {

    @Autowired
    private ProductoRepository productoRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductoPublicService.class);

    public ProductoRespuestaDTO consultarProductoPorId(
            Long id
    ) throws ProductoException {
        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);

        if (productoOptional.isEmpty())
            throw new ProductoException(HttpStatus.NOT_FOUND, "producto_no_encontrado", "Producto no encontrado");

        return mapProductoRespuestaDTO(productoOptional.get());
    }

    public List<ProductoRespuestaDTO> consultarProductos(
    ) throws ProductoException {
        return productoRepository.findAll().stream().map(ProductoMapper::mapProductoRespuestaDTO).collect(Collectors.toList());
    }
}