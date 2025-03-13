package com.example.areplab6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.disable())  //  Deshabilitar CORS en desarrollo (habilitar en producción)
            .csrf(csrf -> csrf.disable())  //  Deshabilitar CSRF para permitir POST desde Postman/frontend
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v1/login/login", "/v1/login/register").permitAll() 
                .requestMatchers("/api/**").permitAll()  
                .requestMatchers("/h2-console/**").permitAll()  // Permitir H2
                .anyRequest().authenticated()) 
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers -> headers.frameOptions().disable());  

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
