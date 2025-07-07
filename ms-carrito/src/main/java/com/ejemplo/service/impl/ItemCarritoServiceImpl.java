package com.ejemplo.service.impl;

import com.ejemplo.model.ItemCarrito;
import com.ejemplo.repository.ItemCarritoRepository;
import com.ejemplo.service.ItemCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCarritoServiceImpl implements ItemCarritoService {

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Override
    public List<ItemCarrito> findAll() {
        return itemCarritoRepository.findAll();
    }

    @Override
    public Optional<ItemCarrito> findById(Long id) {
        return itemCarritoRepository.findById(id);
    }

    @Override
    public ItemCarrito save(ItemCarrito itemCarrito) {
        return itemCarritoRepository.save(itemCarrito);
    }

    @Override
    public void deleteById(Long id) {
        itemCarritoRepository.deleteById(id);
    }
}