package com.impulse.back_end.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handler(MethodArgumentNotValidException exception) {
        ErrorDTO error = new ErrorDTO();

        String message = exception.getAllErrors().stream().map( (e) -> {
            return ((FieldError) e).getDefaultMessage();
        }).collect(Collectors.joining(", "));

        error.setError(new ErrorDTO.InternalErrorDTO("peticion_invalida", message));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({UsuarioException.class})
    public ResponseEntity<ErrorDTO> handler(UsuarioException exception) {
        ErrorDTO error = new ErrorDTO();

        error.setError(new ErrorDTO.InternalErrorDTO(exception.getCode(), exception.getMessage()));

        return ResponseEntity.status(exception.getStatus()).body(error);
    }
}
