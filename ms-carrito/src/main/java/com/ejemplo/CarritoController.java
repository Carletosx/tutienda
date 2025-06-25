package com.ejemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CarritoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/carrito")
    public String carrito(Model model) {
        List<String> productos = restTemplate.getForObject("http://ms-productos:8081/api/productos", List.class);
        model.addAttribute("productos", productos);
        return "carrito";
    }
}
