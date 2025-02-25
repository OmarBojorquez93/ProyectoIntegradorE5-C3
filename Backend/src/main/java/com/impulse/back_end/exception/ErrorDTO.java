package com.impulse.back_end.exception;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

    private InternalErrorDTO error;

    public InternalErrorDTO getError() {
        return error;
    }

    public void setError(InternalErrorDTO error) {
        this.error = error;
    }

    static class InternalErrorDTO implements Serializable {
        private String code;
        private String message;

        public InternalErrorDTO(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
