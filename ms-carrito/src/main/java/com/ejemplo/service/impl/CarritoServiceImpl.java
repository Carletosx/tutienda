package com.ejemplo.service.impl;

import com.ejemplo.model.Carrito;
import com.ejemplo.model.ItemCarrito;
import com.ejemplo.service.CarritoService;
import com.ejemplo.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final Map<Long, Carrito> carritos = new ConcurrentHashMap<>();
    private final ProductoService productoService;

    public CarritoServiceImpl(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public Carrito getCarritoByUsuarioId(Long usuarioId) {
        return carritos.computeIfAbsent(usuarioId, Carrito::new);
    }

    @Override
    public Carrito addItemToCarrito(Long usuarioId, Long productoId, int cantidad) {
        Map<String, Object> productoInfo = productoService.getProducto(productoId);
        if (productoInfo == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        String nombreProducto = (String) productoInfo.get("nombre");
        Double precioUnitario = Double.valueOf(productoInfo.get("precio").toString());

        ItemCarrito nuevoItem = new ItemCarrito(productoId, nombreProducto, cantidad, precioUnitario);
        Carrito carrito = getCarritoByUsuarioId(usuarioId);
        carrito.addItem(nuevoItem);

        return carrito;
    }

    @Override
    public Carrito removeItemFromCarrito(Long usuarioId, Long productoId) {
        Carrito carrito = getCarritoByUsuarioId(usuarioId);
        carrito.removeItem(productoId);
        return carrito;
    }

    @Override
    public void clearCarrito(Long usuarioId) {
        Carrito carrito = carritos.get(usuarioId);
        if (carrito != null) {
            carrito.clear();
        }
    }
}