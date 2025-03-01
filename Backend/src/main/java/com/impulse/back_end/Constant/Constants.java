package com.impulse.back_end.Constant;

public class Constants {

    public static class Headers {
        public static final String SESSION_ID = "session-id";
    }

    public static class PublicRoutes {
        public static final String REGISTRO = "/registro";
        public static final String LOGIN = "/login";

        public static final String[] ALL = {
                REGISTRO, LOGIN
        };
    }

    public static class AdminRoutes {
        public static final String USUARIO = "/usuario";

        public static final String[] ALL = {
                USUARIO
        };
    }
}
