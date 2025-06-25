package com.ejemplo.controller;

import com.ejemplo.model.Venta;
import com.ejemplo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.findById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear-desde-carrito/{usuarioId}")
    public ResponseEntity<Venta> createVentaFromCarrito(@PathVariable Long usuarioId) {
        try {
            Venta nuevaVenta = ventaService.createVentaFromCarrito(usuarioId);
            return ResponseEntity.ok(nuevaVenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}