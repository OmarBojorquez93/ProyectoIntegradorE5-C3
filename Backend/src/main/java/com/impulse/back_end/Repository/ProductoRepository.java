// ProductoRepository.java
// ===========================
package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);

    List<ProductoEntity> findByFechaDisponibleBetween(LocalDate desde, LocalDate hasta);
}

