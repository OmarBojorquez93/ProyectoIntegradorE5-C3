package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.CaracteristicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicaRepository extends JpaRepository<CaracteristicaEntity, Long> {
}
