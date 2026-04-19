package com.example.RestauranteInteligente.repository;

import com.example.RestauranteInteligente.entity.Dish;
import com.example.RestauranteInteligente.entity.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RestauranteRepository {

    private final List<Dish> menu = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong orderSequence = new AtomicLong(1);

    @PostConstruct
    public void initMenu() {
        menu.add(new Dish(1, "Ensalada Cesar", new BigDecimal("12.50"), 10, "entrada"));
        menu.add(new Dish(2, "Sopa de Verduras", new BigDecimal("9.00"), 8, "entrada"));
        menu.add(new Dish(3, "Lomo Saltado", new BigDecimal("25.00"), 20, "principal"));
        menu.add(new Dish(4, "Arroz Chaufa", new BigDecimal("18.50"), 15, "principal"));
        menu.add(new Dish(5, "Limonada", new BigDecimal("6.00"), 3, "bebida"));
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Optional<Order> findOrderById(Long id) {
        return orders.stream().filter(order -> order.getOrderId().equals(id)).findFirst();
    }

    public Order saveOrder(Order order) {
        if (order.getOrderId() == null) {
            order.setOrderId(orderSequence.getAndIncrement());
            orders.add(order);
        }
        return order;
    }
}
