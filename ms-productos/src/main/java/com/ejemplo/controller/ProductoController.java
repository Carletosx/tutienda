package com.ejemplo.controller;

import com.ejemplo.model.Producto;
import com.ejemplo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ejemplo.service.CategoriaService;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String getAllProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "producto-form";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        productoService.findById(id).ifPresent(producto -> model.addAttribute("producto", producto));
        model.addAttribute("categorias", categoriaService.findAll());
        return "producto-form";
    }

    @PostMapping
    public String saveProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        productoService.save(producto);
        redirectAttributes.addFlashAttribute("message", "Producto guardado exitosamente!");
        return "redirect:/productos";
    }



    @GetMapping("/eliminar/{id}")
    public String deleteProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productoService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Producto eliminado exitosamente!");
        return "redirect:/productos";
    }
}