package org.goya.daw2.dwes.d3_ud3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration       // "Spring, esta clase tiene instrucciones de configuración para tu caja de herramientas"
@EnableWebSecurity   // "Activa Spring Security con mis propias reglas"
public class WebSecurityConfig {

    // BEAN 1: El Portero (reglas de qué URLs son públicas y cuáles privadas)
    @Bean
    public SecurityFilterChain cadenaDeFiltrosDeSeguridad(HttpSecurity configuradorHttp) throws Exception {
        configuradorHttp
            // 1. Reglas de autorización de rutas:
            .authorizeHttpRequests((peticiones) -> peticiones
                .requestMatchers("/", "/home").permitAll() // "/" y "/home" son públicas para todos
                .anyRequest().authenticated()              // Cualquier otra ruta (/hello) exige estar logueado
            )
            // 2. Configuración del formulario de inicio de sesión:
            .formLogin((formulario) -> formulario
                .loginPage("/login") // Usa nuestra plantilla en "/login", no el formulario gris por defecto
                .permitAll()         // Deja entrar a todo el mundo a ver "/login"
            )
            // 3. Configuración del cierre de sesión:
            .logout((cierreSesion) -> cierreSesion.permitAll()); // Permite hacer POST a "/logout"

        return configuradorHttp.build(); // Fabrica y entrega el portero a Spring
    }

    // BEAN 2: El Codificador de Contraseñas (sustituye al "new BCryptPasswordEncoder()" manual)
    @Bean
    public PasswordEncoder codificadorDeContrasenas() {
        return new BCryptPasswordEncoder(); // Lo guarda en la caja de herramientas de Spring
    }

    // BEAN 3: El Gestor de Usuarios en Memoria (sustituye al "Map<String, String> USUARIOS")
    // Spring saca automáticamente el Bean 2 y lo mete en el parámetro "codificador"
    @Bean
    public UserDetailsService servicioDeUsuarios(PasswordEncoder codificador) {
        UserDetails usuario1 = User.builder()
                .username("user")
                .password(codificador.encode("password")) // Hashea "password" con BCrypt
                .roles("USER")
                .build();
        
        UserDetails usuario2 = User.builder()
				.username("manu")
				.password(codificador.encode("daw2026"))
				.roles("USER")
				.build();

		UserDetails usuario3 = User.builder()
				.username("profesor")
				.password(codificador.encode("spring2026"))
				.roles("USER")
				.build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3); // Guarda el usuario en memoria y lo entrega a Spring
    }
}
