package com.ejemplo.service;

import com.ejemplo.model.Carrito;

public interface CarritoService {
    
    /**
     * Obtiene el carrito de un usuario específico
     * @param usuarioId ID del usuario
     * @return Carrito del usuario
     */
    Carrito getCarritoByUsuarioId(Long usuarioId);

    /**
     * Agrega un item al carrito
     * @param usuarioId ID del usuario
     * @param productoId ID del producto
     * @param cantidad Cantidad del producto
     * @return Carrito actualizado
     */
    Carrito addItemToCarrito(Long usuarioId, Long productoId, int cantidad);

    /**
     * Elimina un item del carrito
     * @param usuarioId ID del usuario
     * @param productoId ID del producto a eliminar
     * @return Carrito actualizado
     */
    Carrito removeItemFromCarrito(Long usuarioId, Long productoId);

    /**
     * Limpia todos los items del carrito
     * @param usuarioId ID del usuario
     */
    void clearCarrito(Long usuarioId);
}