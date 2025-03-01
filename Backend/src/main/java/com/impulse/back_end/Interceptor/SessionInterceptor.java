package com.impulse.back_end.Interceptor;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Service.SessionService;
import com.impulse.back_end.exception.SessionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request.getServletPath()=" + request.getServletPath());

        boolean isPublicRoute = Arrays.stream(Constants.PublicRoutes.ALL).anyMatch(r -> {
            return request.getServletPath().contains(r);
        });

        if (isPublicRoute) {
            return true;
        }

        String session = request.getHeader(Constants.Headers.SESSION_ID);
        if (session == null || session.isBlank()) {
            throw new SessionException(HttpStatus.FORBIDDEN, "sesion_no_especificada", "El header session-id no fue especificado");
        }

        if (sessionService.consultarSessionById(session) == null) {
            throw new SessionException(HttpStatus.FORBIDDEN, "sesion_no_valida", "Sesion no valida");
        }

        boolean isAdminRoute = Arrays.stream(Constants.AdminRoutes.ALL).anyMatch(r -> {
            return request.getServletPath().contains(r);
        });

        if (isAdminRoute && !sessionService.isAdminSession(session)) {
            throw new SessionException(HttpStatus.UNAUTHORIZED, "sesion_no_autorizada", "Sesion no autorizada");
        }

        return true;
    }
}
