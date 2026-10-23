package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorCodificacion {
	
	@PostMapping("/prueba")
	public String pruebaPost(@RequestParam(name="texto", defaultValue="") String texto) {
		return "<p>"+texto+"</p>";
	}

}
