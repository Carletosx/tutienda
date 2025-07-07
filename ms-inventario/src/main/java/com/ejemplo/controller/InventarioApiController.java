package com.ejemplo.controller;

import com.ejemplo.model.Inventario;
import com.ejemplo.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioApiController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventarios() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        return inventarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> getInventarioByProductoId(@PathVariable Long productoId) {
        Inventario inventario = inventarioService.getInventarioByProductoId(productoId);
        return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        if (!inventarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inventario.setId(id);
        return ResponseEntity.ok(inventarioService.save(inventario));
    }

    @PutMapping("/updateStock/{productoId}")
    public ResponseEntity<Inventario> updateStock(@PathVariable Long productoId, @RequestParam int cantidad) {
        try {
            Inventario inventario = inventarioService.updateStock(productoId, cantidad);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        if (!inventarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inventarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}