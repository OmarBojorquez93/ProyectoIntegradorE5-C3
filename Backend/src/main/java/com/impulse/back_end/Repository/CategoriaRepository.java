package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNombreIgnoreCase(String nombre);
}
