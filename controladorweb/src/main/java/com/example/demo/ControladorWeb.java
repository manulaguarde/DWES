package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

@Controller
public class ControladorWeb implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registro) {
		registro.addViewController("/resultados").setViewName("resultados");
	}

	@GetMapping("/")
	public String showForm(PersonaFormulario personaFormulario) {
		return "formulario";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonaFormulario personaFormulario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "formulario";
		}

		return "redirect:/resultados";
	}
}