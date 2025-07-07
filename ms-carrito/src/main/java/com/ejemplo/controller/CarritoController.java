package com.ejemplo.controller;

import com.ejemplo.model.Carrito;
import com.ejemplo.service.CarritoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/{usuarioId}")
    public String verCarrito(@PathVariable Long usuarioId, Model model) {
        Carrito carrito = carritoService.getCarritoByUsuarioId(usuarioId);
        model.addAttribute("carrito", carrito);
        model.addAttribute("usuarioId", usuarioId);
        return "carrito";
    }

    @PostMapping("/{usuarioId}/agregar")
    public String agregarAlCarrito(@PathVariable Long usuarioId,
                                 @RequestParam Long productoId,
                                 @RequestParam int cantidad) {
        carritoService.addItemToCarrito(usuarioId, productoId, cantidad);
        return "redirect:/carrito/" + usuarioId;
    }

    @PostMapping("/{usuarioId}/eliminar/{productoId}")
    public String eliminarDelCarrito(@PathVariable Long usuarioId,
                                   @PathVariable Long productoId) {
        carritoService.removeItemFromCarrito(usuarioId, productoId);
        return "redirect:/carrito/" + usuarioId;
    }

    @PostMapping("/{usuarioId}/limpiar")
    public String limpiarCarrito(@PathVariable Long usuarioId) {
        carritoService.clearCarrito(usuarioId);
        return "redirect:/carrito/" + usuarioId;
    }

    // API REST endpoints para integración con otros servicios
    @RestController
    @RequestMapping("/api/carrito")
    public static class CarritoApiController {

        private final CarritoService carritoService;

        public CarritoApiController(CarritoService carritoService) {
            this.carritoService = carritoService;
        }

        @GetMapping("/{usuarioId}")
        public Carrito getCarrito(@PathVariable Long usuarioId) {
            return carritoService.getCarritoByUsuarioId(usuarioId);
        }

        @PostMapping("/{usuarioId}/agregar")
        public Carrito agregarItem(@PathVariable Long usuarioId,
                                 @RequestParam Long productoId,
                                 @RequestParam int cantidad) {
            return carritoService.addItemToCarrito(usuarioId, productoId, cantidad);
        }

        @DeleteMapping("/{usuarioId}/eliminar/{productoId}")
        public Carrito eliminarItem(@PathVariable Long usuarioId,
                                  @PathVariable Long productoId) {
            return carritoService.removeItemFromCarrito(usuarioId, productoId);
        }

        @DeleteMapping("/{usuarioId}/limpiar")
        public void limpiar(@PathVariable Long usuarioId) {
            carritoService.clearCarrito(usuarioId);
        }
    }
}
