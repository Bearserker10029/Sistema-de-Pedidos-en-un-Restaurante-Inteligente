package com.example.RestauranteInteligente.controller;

import com.example.RestauranteInteligente.entity.Dish;
import com.example.RestauranteInteligente.entity.Order;
import com.example.RestauranteInteligente.entity.OrderStatus;
import com.example.RestauranteInteligente.repository.RestauranteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping(value = { "/platos", "" })
    public String platos(Model model) {
        model.addAttribute("platos", restauranteRepository.getMenu());
        return "plato";
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("platos", restauranteRepository.getMenu());
        return "crear";
    }

    @GetMapping("/historial")
    public String historial(Model model) {
        model.addAttribute("orders", restauranteRepository.getOrders());
        return "historialpedido";
    }

    @GetMapping("/resumen")
    public String resumen(@RequestParam("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return restauranteRepository.findOrderById(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("puedeAvanzar", order.getEstado() != OrderStatus.ENTREGADO);
                    return "resumenpedido";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Pedido no encontrado");
                    return "redirect:/historial";
                });
    }

    @PostMapping("/crear")
    public String crear(
            @RequestParam("cliente") String cliente,
            @RequestParam("servicio") String servicio,
            @RequestParam(value = "dishIds", required = false) List<Integer> dishIds,
            RedirectAttributes redirectAttributes) {

        if (cliente == null || cliente.isBlank() || servicio == null || servicio.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Cliente y tipo de servicio son obligatorios");
            return "redirect:/crear";
        }

        if (dishIds == null || dishIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar al menos un plato");
            return "redirect:/crear";
        }

        String servicioNormalizado = servicio.trim().toLowerCase(Locale.ROOT);
        if (!servicioNormalizado.equals("para llevar") && !servicioNormalizado.equals("en mesa")) {
            redirectAttributes.addFlashAttribute("error", "Tipo de servicio invalido");
            return "redirect:/crear";
        }

        List<Dish> platosSeleccionados = restauranteRepository.getMenu().stream()
                .filter(dish -> dishIds.contains(dish.getId()))
                .collect(Collectors.toList());

        if (platosSeleccionados.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar al menos un plato valido");
            return "redirect:/crear";
        }

        int tiempoTotal = platosSeleccionados.stream()
                .mapToInt(Dish::getTiempoPreparacion)
                .sum();

        Order order = new Order();
        order.setCliente(cliente.trim());
        order.setServicio(servicioNormalizado);
        order.setPlatos(platosSeleccionados);
        order.setTiempoEstimadoTotal(tiempoTotal);
        order.setEstado(OrderStatus.CREADO);
        restauranteRepository.saveOrder(order);

        return "redirect:/resumen?id=" + order.getOrderId();
    }

    @PostMapping("/pedido/avanzar")
    public String avanzarEstado(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        restauranteRepository.findOrderById(id).ifPresent(order -> order.setEstado(order.getEstado().next()));
        redirectAttributes.addFlashAttribute("ok", "Estado actualizado");
        return "redirect:/resumen?id=" + id;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/platos";
    }

}
