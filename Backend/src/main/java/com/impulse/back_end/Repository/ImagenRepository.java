package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ImagenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<ImagenEntity, Long> {
}
