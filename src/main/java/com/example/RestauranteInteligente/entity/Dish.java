package com.example.RestauranteInteligente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Integer id;
    private String nombre;
    private BigDecimal precio;
    private Integer tiempoPreparacion;
    private String tipo;
}
