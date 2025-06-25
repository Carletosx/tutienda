package com.ejemplo.service;

import com.ejemplo.model.Inventario;
import java.util.List;
import java.util.Optional;

public interface InventarioService {
    List<Inventario> findAll();
    Optional<Inventario> findById(Long id);
    Inventario save(Inventario inventario);
    void deleteById(Long id);
    Inventario updateStock(Long productoId, int cantidad);
    Inventario getInventarioByProductoId(Long productoId);
}