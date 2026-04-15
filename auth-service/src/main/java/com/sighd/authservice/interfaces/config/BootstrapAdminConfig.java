package com.sighd.authservice.interfaces.config;

import com.sighd.authservice.domain.enums.Rol;
import com.sighd.authservice.domain.model.Usuario;
import com.sighd.authservice.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class BootstrapAdminConfig {

    @Bean
    CommandLineRunner initAdmin(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${bootstrap.admin.username:admin}") String username,
            @Value("${bootstrap.admin.password:Admin1234!}") String password,
            @Value("${bootstrap.admin.rol:ADMIN}") Rol rol,
            @Value("${bootstrap.admin.reset-password:true}") boolean resetPassword
    ) {
        return args -> {
            Optional<Usuario> existing = usuarioRepository.findByUsernameAndActivoTrue(username);
            if (existing.isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername(username);
                admin.setPassword(passwordEncoder.encode(password));
                admin.setRol(rol);
                admin.setPacienteId(null);
                admin.setActivo(true);
                usuarioRepository.save(admin);
            } else if (resetPassword) {
                Usuario admin = existing.get();
                admin.setPassword(passwordEncoder.encode(password));
                admin.setRol(rol);
                admin.setActivo(true);
                usuarioRepository.save(admin);
            }
        };
    }
}

