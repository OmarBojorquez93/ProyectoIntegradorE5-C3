package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.ProductoEntity;
import com.impulse.back_end.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findAllByDesdeAfterAndHastaBefore(final LocalDate desde, final LocalDate hasta);

    Optional<ReservaEntity> findOneByProductoAndDesdeAfterAndHastaBefore(final ProductoEntity producto, final LocalDate desde, final LocalDate hasta);

    @Query(value = "from ReservaEntity r where r.producto.id = :productoId and r.desde >= :desde")
    List<ReservaEntity> findAllByProductoAndDesdeGreaterThanEqual(@Param("productoId") final Long idProducto, @Param("desde") final LocalDate desde);
}
