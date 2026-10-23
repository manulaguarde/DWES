package com.example.demo;

public class Persona {
    private String nombre;
    private int edad;

    // Constructor para inicializar los datos fácilmente
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters: ¡MUY IMPORTANTES!
    // Thymeleaf los utiliza internamente para leer y modificar las propiedades.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}