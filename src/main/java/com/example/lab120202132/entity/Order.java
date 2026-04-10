package com.example.lab120202132.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderId;
    private String cliente;
    private String servicio;
    private String estado;
}
