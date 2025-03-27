package com.impulse.back_end.Entity;

public enum UsuarioRole {
    ROLE_USER(0),  // Usuario común
    ROLE_ADMIN(1); // Usuario administrador

    private final int value;

    UsuarioRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UsuarioRole fromValue(int value) {
        for (UsuarioRole role : UsuarioRole.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}

