package com.ejemplo.service;

import com.ejemplo.dto.InventarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventarioClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String INVENTARIO_SERVICE_URL = "http://ms-inventario/inventario";

    public InventarioDTO getInventarioByProductoId(Long productoId) {
        try {
            return restTemplate.getForObject(INVENTARIO_SERVICE_URL + "/producto/" + productoId, InventarioDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizarStock(Long productoId, int cantidad) {
        try {
            restTemplate.put(INVENTARIO_SERVICE_URL + "/updateStock/" + productoId + "?cantidad=" + cantidad, null);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el stock: " + e.getMessage());
        }
    }
}