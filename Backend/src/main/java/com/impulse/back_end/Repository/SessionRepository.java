package com.impulse.back_end.Repository;

import com.impulse.back_end.Entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    Optional<SessionEntity> findByEmail(String email);
    Optional<SessionEntity> findBySession(String session);
    void deleteByEmail(String email);
}
