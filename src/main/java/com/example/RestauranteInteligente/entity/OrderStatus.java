package com.example.RestauranteInteligente.entity;

public enum OrderStatus {
    CREADO,
    EN_PREPARACION,
    LISTO,
    ENTREGADO;

    public OrderStatus next() {
        return switch (this) {
            case CREADO -> EN_PREPARACION;
            case EN_PREPARACION -> LISTO;
            case LISTO, ENTREGADO -> ENTREGADO;
        };
    }
}
