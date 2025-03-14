package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    Optional<ProductoEntity> findByNombre(String nombre);
}
