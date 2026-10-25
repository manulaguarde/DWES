package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorCodificacion {
	
	@PostMapping("/prueba")
	public String pruebaPost(@RequestParam(name="texto", defaultValue="") String texto) {
		return "<p>"+texto+"</p>";
	}
	
	@GetMapping("/prueba-get")
	public String pruebaGet(@RequestParam(name="texto", defaultValue="")String texto,
							@RequestParam(name="nombre", defaultValue="")String nombre) {
		return "<p>"+texto+" "+nombre+"</p>";
	}
	

}
