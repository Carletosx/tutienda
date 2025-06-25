package com.ejemplo.service.impl;

import com.ejemplo.model.Inventario;
import com.ejemplo.repository.InventarioRepository;
import com.ejemplo.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public Optional<Inventario> findById(Long id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Inventario save(Inventario inventario) {
        inventario.setUltimaActualizacion(LocalDateTime.now());
        return inventarioRepository.save(inventario);
    }

    @Override
    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);
    }

    @Override
    public Inventario updateStock(Long productoId, int cantidad) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElse(new Inventario(productoId, 0)); // Create new if not found
        inventario.setStockActual(inventario.getStockActual() + cantidad);
        inventario.setUltimaActualizacion(LocalDateTime.now());
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario getInventarioByProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId).orElse(null);
    }
}