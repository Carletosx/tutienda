package com.ejemplo.service.impl;

import com.ejemplo.model.DetalleVenta;
import com.ejemplo.repository.DetalleVentaRepository;
import com.ejemplo.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void deleteById(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}