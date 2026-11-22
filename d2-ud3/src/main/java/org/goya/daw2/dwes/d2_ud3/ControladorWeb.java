package org.goya.daw2.dwes.d2_ud3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorWeb {
	
	
	private static final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
	private static final Map<String, String> USUARIOS = new HashMap<>();
	
	static {
		USUARIOS.put("admin", encoder.encode("1234"));
		USUARIOS.put("manu", encoder.encode("alumno"));
		USUARIOS.put("profesor", encoder.encode("1234"));
		
		System.out.println(USUARIOS.get("admin"));
		System.out.println(USUARIOS.get("profesor"));
		
	}
	
	@GetMapping("/")
	public String inicio(HttpSession sesion, Model modelo) {
		String usuario = (String) sesion.getAttribute("usuarioLogueado");
		if(usuario == null) {
			return "redirect:/login";
		}
		
		modelo.addAttribute("usuario",usuario);
		modelo.addAttribute("paginaActual", "Inicio");
		return "pagina";
	}
	
	@GetMapping("/pagina1")
	public String pagina1(HttpSession sesion, Model modelo) {
		String usuario = (String) sesion.getAttribute("usuarioLogueado");
		if(usuario == null) {
			return "redirect:/login";
		}
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("paginaActual", "Página 1");
		return "pagina";
	}
	
	@GetMapping("/pagina2")
	public String pagina2(HttpSession sesion, Model modelo) {
		String usuario = (String) sesion.getAttribute("usuarioLogueado");
		if(usuario == null) {
			return "redirect:/login";
		}
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("paginaActual", "Página 2");
		return "pagina";
	}
	
	@GetMapping("/login")
	public String mostrarLogin(HttpSession sesion) {
		if(sesion.getAttribute("usuarioLogueado") != null) {
			return "redirect:/";
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String verificarUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password, HttpSession sesion, Model modelo) {
		
		
		String hashGuardado=USUARIOS.get(usuario);
		if(hashGuardado != null && encoder.matches(password, hashGuardado)) {
			sesion.setAttribute("usuarioLogueado",usuario);
			return "redirect:/";
		}
		
		modelo.addAttribute("error", "Usuario o contraseña incorretos");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/login";
	}

}
