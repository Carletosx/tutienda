package com.ejemplo.controller;


import com.ejemplo.service.ProductoService;
import com.ejemplo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String mostrarTienda(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "tienda";
    }

    @GetMapping("/categoria/{categoriaId}")
    public String mostrarProductosPorCategoria(@PathVariable Long categoriaId, Model model) {
        model.addAttribute("productos", productoService.findByCategoriaId(categoriaId));
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("categoriaSeleccionada", categoriaId);
        return "tienda";
    }

    @GetMapping("/producto/{id}")
    public String mostrarDetalleProducto(@PathVariable Long id, Model model) {
        productoService.findById(id).ifPresent(producto -> {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.findAll());
        });
        return "producto-detalle";
    }
}