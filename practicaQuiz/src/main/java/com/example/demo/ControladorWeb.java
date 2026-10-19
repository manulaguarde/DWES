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
	public String muestraPrimerFormulario(@ModelAttribute("alumno") Alumno alumno) {
		
		return "formularioUno";
	}
	
	@PostMapping("/curso")
	public String muestraSegundoFormulario(@ModelAttribute("alumno") Alumno alumno) {
		
		
		return "formularioDos";
	}
	
	@PostMapping("/anio")
	public String muestraTercerFormulario(@ModelAttribute("alumno") Alumno alumno) {
		
		
		return "formularioTres";
	}
	
	@PostMapping("/conocimientos")
	public String muestraCuartoFormulario(@ModelAttribute("alumno") Alumno alumno) {
		
		
		return "conocimientos";
	}
	
	@PostMapping("/resumen")
	public String muestraResumen(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult errores) {
		
		if(errores.hasFieldErrors("nombre")) return "formularioUno";
		if(errores.hasFieldErrors("curso")) return "formularioDos";
		if(errores.hasFieldErrors("anio")) return "formularioTres";
		
		return "resumen";
	}
	
	@PostMapping("/atrasAFormularioUno")
	public String volverAFormularioUno(@ModelAttribute("alumno") Alumno alumno) {
		
		return "formularioUno";
	}
	
	@PostMapping("/atrasAFormularioDos")
	public String volverAFormularioDos(@ModelAttribute("alumno") Alumno alumno) {
		
		return "formularioDos";
	}
	
	@PostMapping("/atrasAFormularioTres")
	public String volverAFormularioTres(@ModelAttribute("alumno") Alumno alumno) {
		
		return "formularioTres";
	}

}
