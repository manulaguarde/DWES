package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Jugador {
	
	public static final int TOPE=50;
	@NotBlank
	@Size(min=3)
	private String nombre;
	
	
	private int intentos=0;
	
	@Min(1)
	@Max (50)
	private int numeroElegido;
	
	private int numeroAleatorio;
	
	public Jugador() {}
	
	public Jugador(String nombre, int intentos) {
		super();
		this.nombre = nombre;
		this.intentos = intentos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	public int getNumeroElegido() {
		return numeroElegido;
	}
	
	public void setNumeroElegido(int numeroElegido) {
		this.numeroElegido=numeroElegido;
	}
	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", intentos=" + intentos + "]";
	}
	
	
	public static List<Jugador> ejemploFakeListaJugadores() {
		List<Jugador> resultado = new ArrayList<Jugador>();
		resultado.add(new Jugador("Alumno7",2));
		resultado.add(new Jugador("Alumno6",4));
		resultado.add(new Jugador("Alumno1",5));
		resultado.add(new Jugador("Profesor2",6));
		resultado.add(new Jugador("Profesor1",7));
		return resultado;
	}

	public int getNumeroAleatorio() {
		return numeroAleatorio;
	}

	public void setNumeroAleatorio(int numeroAleatorio) {
		this.numeroAleatorio = numeroAleatorio;
	}
}
