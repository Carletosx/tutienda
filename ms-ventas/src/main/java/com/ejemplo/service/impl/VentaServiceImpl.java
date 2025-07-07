package com.ejemplo.service.impl;

import com.ejemplo.model.DetalleVenta;
import com.ejemplo.model.Venta;
import com.ejemplo.repository.VentaRepository;
import com.ejemplo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CARRITO_SERVICE_URL = "http://ms-carrito/carritos/";


    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Venta createVentaFromCarrito(Long usuarioId) {
        // 1. Obtener el carrito del usuario desde ms-carrito
        // Using LinkedHashMap to correctly cast the response from RestTemplate
        LinkedHashMap<String, Object> carritoResponse = (LinkedHashMap<String, Object>) restTemplate.getForObject(CARRITO_SERVICE_URL + usuarioId, LinkedHashMap.class);

        if (carritoResponse == null || !carritoResponse.containsKey("items")) {
            throw new RuntimeException("Carrito no encontrado o vacío para el usuario: " + usuarioId);
        }

        List<LinkedHashMap<String, Object>> itemsCarrito = (List<LinkedHashMap<String, Object>>) carritoResponse.get("items");

        if (itemsCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío para el usuario: " + usuarioId);
        }

        Venta venta = new Venta();
        venta.setUsuarioId(usuarioId);
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        BigDecimal totalVenta = BigDecimal.ZERO;

        // 2. Iterar sobre los ítems del carrito y crear detalles de venta
        for (LinkedHashMap<String, Object> item : itemsCarrito) {
            Long productoId = ((Number) item.get("productoId")).longValue();
            String nombreProducto = (String) item.get("nombreProducto");
            Integer cantidad = (Integer) item.get("cantidad");
            BigDecimal precioUnitario = new BigDecimal(item.get("precioUnitario").toString());

            DetalleVenta detalle = new DetalleVenta(productoId, nombreProducto, cantidad, precioUnitario);
            detallesVenta.add(detalle);
            totalVenta = totalVenta.add(precioUnitario.multiply(BigDecimal.valueOf(cantidad)));

            // Opcional: Actualizar stock en ms-productos (esto requeriría un endpoint en ms-productos)
            // restTemplate.put(PRODUCTO_SERVICE_URL + productoId + "/restarStock?cantidad=" + cantidad, null);
        }

        venta.setDetalles(detallesVenta);
        venta.setTotal(totalVenta);

        // 3. Guardar la venta
        Venta savedVenta = ventaRepository.save(venta);

        // 4. Limpiar el carrito después de la venta (esto requeriría un endpoint en ms-carrito)
        restTemplate.delete(CARRITO_SERVICE_URL + usuarioId + "/clear");

        return savedVenta;
    }
}