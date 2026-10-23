package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(FormularioPersona fp) {
		return "formulario";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid FormularioPersona pf, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "formulario";
		}

		return "redirect:/results";
	}
}
