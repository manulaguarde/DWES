package com.example.demo;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class Alumno {
	
	@NotBlank
	@Size(min=2, message="debe tener al menos dos carácteres")
	private String nombre;
	
	@NotBlank
	private String curso;
	
	@NotBlank
	private String anio;
	
	
	private List<String> conocimientos;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public List<String> getConocimientos() {
		return conocimientos;
	}
	public void setConocimientos(List<String> conocimientos) {
		this.conocimientos = conocimientos;
	}
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", curso=" + curso + ", anio=" + anio + ", conocimientos=" + conocimientos
				+ "]";
	}

}
