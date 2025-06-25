package com.ejemplo.service;

import com.ejemplo.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
}