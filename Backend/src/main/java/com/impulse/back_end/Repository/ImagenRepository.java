package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ImagenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<ImagenEntity, Long> {
    Optional<ImagenEntity> findByRuta(String ruta);
}
