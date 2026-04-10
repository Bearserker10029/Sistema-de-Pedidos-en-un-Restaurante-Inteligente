package com.example.lab120202132.controller;

import com.example.lab120202132.entity.Dish;
import com.example.lab120202132.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class RestauranteController {

    @GetMapping("/platos")
    public String platos(Model model){
        ArrayList<Dish> platos = new ArrayList<>();
        platos.add(new Dish("Ensalada Cesar","12.5","10","entrada"));
        platos.add(new Dish("Sopa de Verduras","9","8","entrada"));
        platos.add(new Dish("Lomo Saltado","25","20","principal"));
        platos.add(new Dish("Arroz Chaufa","18.5","15","principal"));
        platos.add(new Dish("Limonada","6","3","bebida"));
        model.addAttribute("platos", platos);
        return "plato";
    }
    @GetMapping("/historial")
    public String historial(Model model){
        List<Order> order = new ArrayList<>();
        model.addAttribute("order", order);
        return "historialpedido";
    }
    @PostMapping("/crear")
    public String crear(Model model){

        return "crear";
    }

}
