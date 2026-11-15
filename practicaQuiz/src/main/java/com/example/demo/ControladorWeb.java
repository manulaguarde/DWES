package com.example.demo;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class ControladorWeb {

	
	@GetMapping("/")
	public String muestraPrimerFormulario(Model modelo) {
		modelo.addAttribute("alumno", new Alumno());
		return "formularioUno";
	}

	
	@PostMapping("/curso")
	public String muestraSegundoFormulario(@Valid Alumno datosFormulario, BindingResult errores, HttpServletResponse response,
			Model modelo) {

		if (errores.hasFieldErrors("nombre")) {
			return "formularioUno";
		}

		//codificamos el nombre por si tiene espacios o tildes
		String nombreCodificado = URLEncoder.encode(datosFormulario.getNombre(), StandardCharsets.UTF_8);
		
		//creamos la cookie
		Cookie cookieNombre = new Cookie("nombre", nombreCodificado);
		
		//hacemos la cookie válida para la raíz / y para todas las subrutas que cuelguen de ella
		cookieNombre.setPath("/");
		
		//la añadimos a la respuesta HTTP
		response.addCookie(cookieNombre);

		//pasamos el alumno al modelo para formularioDos.html
		modelo.addAttribute("alumno", datosFormulario);
		return "formularioDos";
	}

	
	@PostMapping("/anio")
	public String muestraTercerFormulario(@Valid Alumno datosFormulario, BindingResult errores,	@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			HttpServletResponse response, Model modelo) {

		if (errores.hasFieldErrors("curso")) {
			
			//si hay un error enviamos la cookie decodificada para que pueda ser visualizada en la plantilla anterior
			datosFormulario.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
			modelo.addAttribute("alumno", datosFormulario);
			return "formularioDos";
		}

		
		String cursoCodificado = URLEncoder.encode(datosFormulario.getCurso(), StandardCharsets.UTF_8);
		Cookie cookieCurso = new Cookie("curso", cursoCodificado);
		cookieCurso.setPath("/");		
		response.addCookie(cookieCurso);

		//reconstruimos el objeto Alumno completo para la siguiente vista
		datosFormulario.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		modelo.addAttribute("alumno", datosFormulario);
		return "formularioTres";
	}

	@PostMapping("/conocimientos")
	public String muestraCuartoFormulario(
			@Valid Alumno datosFormulario,
			BindingResult errores,
			@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			@CookieValue(value = "curso", defaultValue = "") String cursoCookie,
			HttpServletResponse response,
			Model modelo) {

		if (errores.hasFieldErrors("anio")) {
			datosFormulario.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
			datosFormulario.setCurso(URLDecoder.decode(cursoCookie, StandardCharsets.UTF_8));
			modelo.addAttribute("alumno", datosFormulario);
			return "formularioTres";
		}

		String anioCodificado = URLEncoder.encode(datosFormulario.getAnio(), StandardCharsets.UTF_8);
		Cookie cookieAnio = new Cookie("anio", anioCodificado);
		cookieAnio.setPath("/");
		response.addCookie(cookieAnio);

		datosFormulario.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		datosFormulario.setCurso(URLDecoder.decode(cursoCookie, StandardCharsets.UTF_8));
		modelo.addAttribute("alumno", datosFormulario);
		return "conocimientos";
	}

	
	@PostMapping("/resumen")
	public String muestraResumen(
			Alumno datosFormulario,
			@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			@CookieValue(value = "curso", defaultValue = "") String cursoCookie,
			@CookieValue(value = "anio", defaultValue = "") String anioCookie,
			HttpServletResponse response,
			Model modelo) {

		datosFormulario.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		datosFormulario.setCurso(URLDecoder.decode(cursoCookie, StandardCharsets.UTF_8));
		datosFormulario.setAnio(URLDecoder.decode(anioCookie, StandardCharsets.UTF_8));

		modelo.addAttribute("alumno", datosFormulario);

		// eliminamos las cookies del navegador al completar el cuestionario
		String[] nombresCookies = {"nombre", "curso", "anio"};
		for (String nombre : nombresCookies) {
			Cookie cookieBorrar = new Cookie(nombre, "");
			cookieBorrar.setPath("/");
			cookieBorrar.setMaxAge(0);
			response.addCookie(cookieBorrar);
		}

		return "resumen";
	}

	

	@PostMapping("/atrasAFormularioUno")
	public String volverAFormularioUno(
			@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			Model modelo) {
		Alumno alumno = new Alumno();
		alumno.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		modelo.addAttribute("alumno", alumno);
		return "formularioUno";
	}

	@PostMapping("/atrasAFormularioDos")
	public String volverAFormularioDos(
			@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			@CookieValue(value = "curso", defaultValue = "") String cursoCookie,
			Model modelo) {
		Alumno alumno = new Alumno();
		alumno.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		alumno.setCurso(URLDecoder.decode(cursoCookie, StandardCharsets.UTF_8));
		modelo.addAttribute("alumno", alumno);
		return "formularioDos";
	}

	@PostMapping("/atrasAFormularioTres")
	public String volverAFormularioTres(
			@CookieValue(value = "nombre", defaultValue = "") String nombreCookie,
			@CookieValue(value = "curso", defaultValue = "") String cursoCookie,
			@CookieValue(value = "anio", defaultValue = "") String anioCookie,
			Model modelo) {
		Alumno alumno = new Alumno();
		alumno.setNombre(URLDecoder.decode(nombreCookie, StandardCharsets.UTF_8));
		alumno.setCurso(URLDecoder.decode(cursoCookie, StandardCharsets.UTF_8));
		alumno.setAnio(URLDecoder.decode(anioCookie, StandardCharsets.UTF_8));
		modelo.addAttribute("alumno", alumno);
		return "formularioTres";
	}
}