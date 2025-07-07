package com.ejemplo.service;

import com.ejemplo.model.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface ItemCarritoService {
    List<ItemCarrito> findAll();
    Optional<ItemCarrito> findById(Long id);
    ItemCarrito save(ItemCarrito itemCarrito);
    void deleteById(Long id);
}