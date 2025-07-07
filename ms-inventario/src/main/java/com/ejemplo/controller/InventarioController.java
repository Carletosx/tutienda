package com.ejemplo.controller;

import com.ejemplo.model.Inventario;
import com.ejemplo.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public String getAllInventarios(Model model) {
        model.addAttribute("inventarios", inventarioService.findAll());
        return "inventario";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("inventario", new Inventario());
        return "inventario-form";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        inventarioService.findById(id).ifPresent(inventario -> 
            model.addAttribute("inventario", inventario));
        return "inventario-form";
    }

    @PostMapping("/guardar")
    public String saveInventario(@ModelAttribute Inventario inventario, RedirectAttributes redirectAttributes) {
        inventarioService.save(inventario);
        redirectAttributes.addFlashAttribute("mensajeExito", "Inventario guardado exitosamente!");
        return "redirect:/inventario";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteInventario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        inventarioService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "Inventario eliminado exitosamente!");
        return "redirect:/inventario";
    }
}