package com.example.lab120202132.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dish {
    private String nombre;
    private String precio;
    private String tiempo;
    private String tipo;
    private Integer id;

    public Dish(String nombre, String precio, String tiempo, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

}
