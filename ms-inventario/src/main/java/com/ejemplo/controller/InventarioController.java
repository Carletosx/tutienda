package com.ejemplo.controller;

import com.ejemplo.model.Inventario;
import com.ejemplo.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventario() {
        List<Inventario> inventarios = inventarioService.findAll();
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.findById(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> getInventarioByProductoId(@PathVariable Long productoId) {
        Inventario inventario = inventarioService.getInventarioByProductoId(productoId);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        Inventario newInventario = inventarioService.save(inventario);
        return ResponseEntity.ok(newInventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        return inventarioService.findById(id)
                .map(existingInventario -> {
                    existingInventario.setProductoId(inventario.getProductoId());
                    existingInventario.setStockActual(inventario.getStockActual());
                    return ResponseEntity.ok(inventarioService.save(existingInventario));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateStock/{productoId}")
    public ResponseEntity<Inventario> updateStock(@PathVariable Long productoId, @RequestParam int cantidad) {
        Inventario updatedInventario = inventarioService.updateStock(productoId, cantidad);
        return ResponseEntity.ok(updatedInventario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}