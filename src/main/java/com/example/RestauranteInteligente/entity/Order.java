package com.example.RestauranteInteligente.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long orderId;
    private String cliente;
    private String servicio;
    private List<Dish> platos = new ArrayList<>();
    private Integer tiempoEstimadoTotal;
    private OrderStatus estado;
}
