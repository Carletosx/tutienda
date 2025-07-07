package com.ejemplo.controller;

import com.ejemplo.model.Categoria;
import com.ejemplo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String getAllCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias";
    }

    @GetMapping("/nueva")
    public String showCreateForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria-form";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        categoriaService.findById(id).ifPresent(categoria -> 
            model.addAttribute("categoria", categoria));
        return "categoria-form";
    }

    @PostMapping("/guardar")
    public String saveCategoria(@ModelAttribute Categoria categoria, RedirectAttributes redirectAttributes) {
        categoriaService.save(categoria);
        redirectAttributes.addFlashAttribute("mensajeExito", "Categoría guardada exitosamente!");
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoriaService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "Categoría eliminada exitosamente!");
        return "redirect:/categorias";
    }
}