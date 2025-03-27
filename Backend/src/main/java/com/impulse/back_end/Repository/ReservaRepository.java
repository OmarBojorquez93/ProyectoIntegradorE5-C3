package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findAllByDesdeAfterAndHastaBefore(final LocalDate desde, final LocalDate hasta);

    Optional<ReservaEntity> findOneByProductoAndDesdeAfterAndHastaBefore(final ProductoEntity producto, final LocalDate desde, final LocalDate hasta);
}
