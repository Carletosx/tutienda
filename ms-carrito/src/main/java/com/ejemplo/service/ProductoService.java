package com.ejemplo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProductoService {

    private final RestTemplate restTemplate;
    private final String productosServiceUrl;

    public ProductoService(RestTemplate restTemplate, @Value("${productos.service.url}") String productosServiceUrl) {
        this.restTemplate = restTemplate;
        this.productosServiceUrl = productosServiceUrl;
    }

    public Map<String, Object> getProducto(Long productoId) {
        String url = productosServiceUrl + "/productos/" + productoId;
        return restTemplate.getForObject(url, Map.class);
    }

    public boolean existeProducto(Long productoId) {
        try {
            String url = productosServiceUrl + "/productos/" + productoId;
            return restTemplate.getForObject(url, Map.class) != null;
        } catch (Exception e) {
            return false;
        }
    }
}