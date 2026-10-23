package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;



@Controller
public class SaludoControlador {
	
	@GetMapping("/saludo")
	public String saludo(@RequestParam(name="nombre", required=false, defaultValue="Mundo") String nombre, Model modelo) {//es importante que importemos de Model
																														//la segunda librería que nos aparece
		modelo.addAttribute("nombre", nombre);
		return "holaMundo";
	}
	
	@GetMapping("/saludoFormulario")
	public String formulario(Model model) {
		model.addAttribute("saludo", new Saludo());
		return "formularioSaludo";
	}

	@PostMapping("/mostrarResultados")
	public String enviarFormulario(@ModelAttribute Saludo saludo, Model modelo) {
		modelo.addAttribute("saludo", saludo);
		return "resultados";
	}

}


