package com.impulse.back_end.Constant;

public class Constants {

    public static class Headers {
        public static final String SESSION_ID = "session-id";
    }

    public static class PublicRoutes {
        public static final String ERROR = "/error";
        public static final String REGISTRO = "/registro";
        public static final String LOGIN = "/login";
        public static final String RESERVA = "/reserva";
        public static final String PRODUCTO = "/public/producto";
        public static final String CATEGORIA = "/public/categoria";

        public static final String[] ALL = {
                ERROR, REGISTRO, LOGIN, PRODUCTO, CATEGORIA
        };
    }

    public static class AdminRoutes {
        public static final String USUARIO = "/usuario";
        public static final String PRODUCTO = "/admin/producto";
        public static final String CATEGORIA = "/admin/categoria";

        public static final String[] ALL = {
                USUARIO, PRODUCTO, CATEGORIA
        };
    }
}
