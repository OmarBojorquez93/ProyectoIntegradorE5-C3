package com.impulse.back_end.Service;

import com.impulse.back_end.Dto.CategoriaPeticionDTO;
import com.impulse.back_end.Dto.CategoriaRespuestaDTO;
import com.impulse.back_end.Entity.CategoriaEntity;
import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Repository.CategoriaRepository;
import com.impulse.back_end.Repository.ProductoRepository;
import com.impulse.back_end.exception.CategoriaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.impulse.back_end.mapper.ProductoMapper.mapProductoRespuestaDTO;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

    public CategoriaEntity consultarOCrear(String nombre)  {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findByNombreIgnoreCase(nombre);
        return categoriaOptional.orElseGet(() -> categoriaRepository.save(new CategoriaEntity(nombre)));
    }

    public CategoriaRespuestaDTO registrarCategoria(
            String sessionId,
            CategoriaPeticionDTO categoriaPeticionDTO
    ) throws CategoriaException {
        logger.info("registrarCategoria --Session:[{}] --Peticion:[{}]",
                sessionId, categoriaPeticionDTO
        );

        Optional<CategoriaEntity> categoriaEntityOptional = categoriaRepository.findByNombreIgnoreCase(categoriaPeticionDTO.getNombre());
        if (categoriaEntityOptional.isPresent()) {
            throw new CategoriaException(HttpStatus.CONFLICT, "categoria_invalida", "La categoria con el nombre especificado ya existe");
        }

        CategoriaEntity categoriaEntity = categoriaRepository.save(
                new CategoriaEntity(categoriaPeticionDTO.getNombre())
        );

        return new CategoriaRespuestaDTO(
                categoriaEntity.getId(),
                categoriaEntity.getNombre()
        );
    }

    public CategoriaRespuestaDTO modificarCategoriaPorId(
            String sessionId,
            Long id,
            CategoriaPeticionDTO categoriaPeticionDTO
    ) throws CategoriaException {
        logger.info("modificarCategoriaPorId --Session:[{}] --Id:[{}] --Peticion:[{}]",
                sessionId, id, categoriaPeticionDTO
        );

        Optional<CategoriaEntity> categoriaEntityOptional = categoriaRepository.findByNombreIgnoreCase(categoriaPeticionDTO.getNombre());
        if (categoriaEntityOptional.isPresent() && !Objects.equals(categoriaEntityOptional.get().getId(), id)) {
            throw new CategoriaException(HttpStatus.CONFLICT, "categoria_invalida", "Ya existe otra categoria con el nombre especificado");
        }

        Optional<CategoriaEntity> categoriaEntityOptional2 = categoriaRepository.findById(id);
        if (categoriaEntityOptional2.isEmpty()) {
            throw new CategoriaException(HttpStatus.NOT_FOUND, "categoria_no_encontrada", "La categoria con el id especificado no existe");
        }

        CategoriaEntity categoriaEntity = categoriaEntityOptional2.get();

        categoriaEntity.setNombre(categoriaPeticionDTO.getNombre());

        categoriaEntity = categoriaRepository.save(categoriaEntity);

        return new CategoriaRespuestaDTO(
                categoriaEntity.getId(),
                categoriaEntity.getNombre()
        );
    }

    public void eliminarCategoriaPorId(
            String sessionId,
            Long id
    ) throws CategoriaException {
        logger.info("eliminarCategoriaPorId --Session:[{}] --Id:[{}]", sessionId, id);

        Optional<CategoriaEntity> categoriaEntityOptional = categoriaRepository.findById(id);
        if (categoriaEntityOptional.isEmpty()) {
            throw new CategoriaException(HttpStatus.NOT_FOUND, "categoria_no_encontrada", "La categoria con el id especificado no existe");
        }

        CategoriaEntity categoriaEntity = categoriaEntityOptional.get();

        List<ProductoEntity> productos = productoRepository.findByCategoria(categoriaEntity);
        if (!productos.isEmpty()) {
            throw new CategoriaException(HttpStatus.CONFLICT, "categoria_en_uso", "La categoria con el id especificado no puede eliminar porque tiene productos relacionados");
        }

        categoriaRepository.deleteById(id);
    }

    public CategoriaRespuestaDTO consultarCategoriaPorId(
            Long id
    ) throws CategoriaException {
        Optional<CategoriaEntity> categoriaEntityOptional = categoriaRepository.findById(id);
        if (categoriaEntityOptional.isEmpty()) {
            throw new CategoriaException(HttpStatus.NOT_FOUND, "categoria_no_encontrada", "La categoria con el id especificado no existe");
        }

        return new CategoriaRespuestaDTO(
                categoriaEntityOptional.get().getId(),
                categoriaEntityOptional.get().getNombre()
        );
    }

    public List<CategoriaRespuestaDTO> consultarCategorias(
    ) throws CategoriaException {
        return categoriaRepository.findAll().stream().map(categoriaEntity -> {
            return new CategoriaRespuestaDTO(categoriaEntity.getId(), categoriaEntity.getNombre());
        }).collect(Collectors.toList());
    }
}