package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("jugador")
public class ControladorAdivina {
	
	@ModelAttribute("jugador")
	public Jugador crearJugador() {
		return new Jugador();
		
	}

	private int contadorIntentos=1;
	public static final int MAX_INTENTOS=3;
	@GetMapping("/")
	public String muestraFormularioNombre(@ModelAttribute ("jugador") Jugador jugador) {
		
		return "nombre";
	}
	
	@PostMapping("/juego")
	public String iniciaJuego(@Valid @ModelAttribute("jugador") Jugador jugador, BindingResult bindingResult, Model modelo) {
		jugador.setNumeroAleatorio((int)(Math.random()*50+1));
		jugador.setIntentos(contadorIntentos);
		modelo.addAttribute("maxIntentos", MAX_INTENTOS);
		if (bindingResult.hasFieldErrors("nombre")) {
			return "nombre";
		}
		return "juego";
	}
	
	@PostMapping("/jugar")
	public String jugar(@Valid @ModelAttribute("jugador") Jugador jugador,BindingResult bindingResult, Model modelo) {
		
		modelo.addAttribute("maxIntentos", MAX_INTENTOS);
		contadorIntentos++;
		jugador.setIntentos(contadorIntentos);
		
		if(bindingResult.hasFieldErrors("numeroElegido")) {
			return "juego";
		}
		if(jugador.getNumeroElegido()>jugador.getNumeroAleatorio() && jugador.getIntentos()<=MAX_INTENTOS) {
			
			modelo.addAttribute("mensaje", "El número es menor");
			return "juego";
		}if(jugador.getNumeroElegido()<jugador.getNumeroAleatorio() && jugador.getIntentos()<=MAX_INTENTOS) {
			
			modelo.addAttribute("mensaje", "El número es mayor");
			return "juego";
		}if (jugador.getNumeroElegido()==jugador.getNumeroAleatorio()) {
			Jugador.ejemploFakeListaJugadores().add(jugador);
			return "ganador";
		}
		contadorIntentos=1;
		Jugador.ejemploFakeListaJugadores().add(jugador);
		return "perdedor";
		
	}
	
	@GetMapping("/resultados")
	public String devuelveResultados(@ModelAttribute ("resultados") Jugador jugador) {
		Jugador.ejemploFakeListaJugadores().add(jugador);
		return "resultados";
	}
}
