package com.ejemplo.service;

import com.ejemplo.model.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    List<DetalleVenta> findAll();
    Optional<DetalleVenta> findById(Long id);
    DetalleVenta save(DetalleVenta detalleVenta);
    void deleteById(Long id);
}