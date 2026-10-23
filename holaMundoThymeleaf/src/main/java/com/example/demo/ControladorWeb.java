package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorWeb {

    @GetMapping("/")
    public String holaMundo(@RequestParam(name="nombre", defaultValue="mundo cruel") String nombre, Model modelo) {
        // Intermediario: añadimos el valor capturado al modelo para enviarlo a la vista HTML
        modelo.addAttribute("nombre", nombre);
        
        // Retornamos el nombre de la plantilla sin la extensión .html
        return "holaMundo";
    }
    
    @GetMapping("/persona")
    public String mostrarPersona(Model modelo) {
        // 1. Creamos un objeto de la clase Persona con datos de ejemplo
        Persona p = new Persona("Laura", 25);
        
        // 2. Guardamos el objeto completo en el modelo con la clave "persona"
        modelo.addAttribute("persona", p);
        
        // 3. Devolvemos el nombre de la plantilla HTML (detallesPersona.html)
        return "detallesPersona";
    }
    
    @GetMapping("/listaPersonas")
    public String listarPersonas(Model modelo) {
        // 1. Creamos una lista de objetos Persona
        List<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("Laura", 25));
        listaPersonas.add(new Persona("Carlos", 30));
        listaPersonas.add(new Persona("Elena", 19));

        // 2. Pasamos la lista completa al modelo
        modelo.addAttribute("personas", listaPersonas);

        // 3. Devolvemos la plantilla listaPersonas.html
        return "listaDePersonas";
    }
    
    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "formulario"; // Abre formulario.html
    }

    // 2. Procesa los datos cuando el usuario le da al botón 'Enviar' (solicitud POST)
    @PostMapping("/guardarPersona")
    public String recibirFormulario(@RequestParam(name="nombre") String nombre,
                                    @RequestParam(name="edad") int edad,
                                    Model modelo) {
        
        // Creamos un objeto Persona con los datos capturados del formulario
        Persona p = new Persona(nombre, edad);
        
        // Pasamos la persona creada al modelo para mostrar un mensaje de confirmación
        modelo.addAttribute("personaGuardada", p);
        
        // Retornamos la plantilla de éxito
        return "resultadoFormulario";
    }
}