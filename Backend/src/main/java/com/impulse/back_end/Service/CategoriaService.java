package com.impulse.back_end.Service;

import com.impulse.back_end.Entity.CategoriaEntity;
import com.impulse.back_end.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaEntity consultarOCrear(String nombre)  {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findByNombreIgnoreCase(nombre);
        return categoriaOptional.orElseGet(() -> categoriaRepository.save(new CategoriaEntity(nombre)));
    }
}