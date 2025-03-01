package com.impulse.back_end.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends Exception {

    private HttpStatus status;
    private String code;


    public LoginException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
