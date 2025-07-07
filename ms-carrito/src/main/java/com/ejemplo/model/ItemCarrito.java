package com.ejemplo.model;

public class ItemCarrito {
    private Long productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemCarrito() {
    }

    public ItemCarrito(Long productoId, String nombreProducto, int cantidad, double precioUnitario) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.calcularSubtotal();
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.calcularSubtotal();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void calcularSubtotal() {
        this.subtotal = this.cantidad * this.precioUnitario;
    }
}