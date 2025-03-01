package com.impulse.back_end.Service;

import com.impulse.back_end.Entity.SessionEntity;
import com.impulse.back_end.Entity.UsuarioEntity;
import com.impulse.back_end.Entity.UsuarioRole;
import com.impulse.back_end.Repository.SessionRepository;
import com.impulse.back_end.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearSession(String email) {
        return sessionRepository.save(
                new SessionEntity(email, UUID.randomUUID().toString())
        ).getSession();
    }

    public void borrarSession(String email) {
        sessionRepository.deleteByEmail(email);
    }

    public String consultarSessionByEmail(String email) {
        return sessionRepository.findByEmail(email).map(SessionEntity::getSession).orElse(null);
    }

    public String consultarSessionById(String session) {
        return sessionRepository.findBySession(session).map(SessionEntity::getSession).orElse(null);
    }

    public boolean isAdminSession(String session) {
        SessionEntity sessionEntity = sessionRepository.findBySession(session).orElse(null);

        if (sessionEntity == null) {
            return false;
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(sessionEntity.getEmail()).orElse(null);

        return usuarioEntity != null && usuarioEntity.getUsuarioRole() == UsuarioRole.ROLE_ADMIN;
    }
}
