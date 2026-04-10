package com.example.lab120202132.entity;

import lombok.*;

@Getter
@Setter
public class Order {

    private String orderId;
    private String cliente;
    private String servicio;
    private String estado;

    public Order(String cliente, String servicio, String estado) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.estado = estado;
    }
}
