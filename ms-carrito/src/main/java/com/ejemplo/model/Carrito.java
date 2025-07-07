package com.ejemplo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private Long usuarioId;
    private List<ItemCarrito> items;
    private double total;

    public Carrito(Long usuarioId) {
        this.usuarioId = usuarioId;
        this.items = new ArrayList<>();
        this.total = 0.0;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    public void addItem(ItemCarrito item) {
        // Buscar si el producto ya existe en el carrito
        for (ItemCarrito existingItem : items) {
            if (existingItem.getProductoId().equals(item.getProductoId())) {
                existingItem.setCantidad(existingItem.getCantidad() + item.getCantidad());
                calcularTotal();
                return;
            }
        }
        // Si no existe, agregar el nuevo item
        items.add(item);
        calcularTotal();
    }

    public void removeItem(Long productoId) {
        items.removeIf(item -> item.getProductoId().equals(productoId));
        calcularTotal();
    }

    public void clear() {
        items.clear();
        total = 0.0;
    }

    private void calcularTotal() {
        total = items.stream()
                .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
    }
}