package com.ejemplo.controller;

import com.ejemplo.model.Producto;
import com.ejemplo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.findById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(producto.getNombre());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setPrecio(producto.getPrecio());
                    existingProducto.setStock(producto.getStock());
                    existingProducto.setCategoria(producto.getCategoria());
                    return ResponseEntity.ok(productoService.save(existingProducto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.findById(id).isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}