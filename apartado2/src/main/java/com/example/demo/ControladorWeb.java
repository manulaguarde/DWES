package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class ControladorWeb {

    @GetMapping("/")
    public String mostrarFormulario(@ModelAttribute("alumnoForm") AlumnoForm alumnoForm) {
        return "formulario";
    }

    @PostMapping("/guardar")
    public String procesarFormulario(@Valid @ModelAttribute("alumnoForm") AlumnoForm alumnoForm, 
                                     BindingResult bindingResult) {

        // Si falla alguna validación backend, recarga la misma página mostrando errores y conservando los datos
        if (bindingResult.hasErrors()) {
            return "formulario"; 
        }

        return "resumen";
    }
}