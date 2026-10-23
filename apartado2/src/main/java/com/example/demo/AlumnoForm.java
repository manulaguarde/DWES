package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlumnoForm {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "Debe tener al menos 2 letras")
    private String nombre;

    @NotBlank(message = "Selecciona un curso")
    private String curso;

    
    private ArrayList<String> hobbies;

    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public ArrayList<String> getHobbies() { return hobbies; }
    public void setHobbies(ArrayList<String> hobbies) { this.hobbies = hobbies; }
}