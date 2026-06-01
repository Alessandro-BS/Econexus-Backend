package com.econexus.backend.config;

import com.econexus.backend.model.entity.Usuario;
import com.econexus.backend.model.enums.RolEnum;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            List<Usuario> initialUsers = Arrays.asList(
                Usuario.builder()
                    .nombreCompleto("Fabrizio Alessandro Bustamante Soria")
                    .email("fbustamante@econexus.com")
                    .telefono("979526130")
                    .rol(RolEnum.ADMIN)
                    .estado(EstadoEnum.ACTIVO)
                    .passwordHash(passwordEncoder.encode("bustamante123"))
                    .createdAt(LocalDateTime.of(2026, 4, 25, 10, 0))
                    .build(),
                Usuario.builder()
                    .nombreCompleto("Shayuri Kiara Garcia Ortega")
                    .email("sgarcia@econexus.com")
                    .telefono("972173803")
                    .rol(RolEnum.SUPERVISOR)
                    .estado(EstadoEnum.ACTIVO)
                    .passwordHash(passwordEncoder.encode("garcia123"))
                    .createdAt(LocalDateTime.of(2026, 4, 25, 10, 0))
                    .build(),
                Usuario.builder()
                    .nombreCompleto("Walter Isaac Mantari Licapa")
                    .email("wmantari@econexus.com")
                    .telefono("934075905")
                    .rol(RolEnum.OPERADOR)
                    .estado(EstadoEnum.ACTIVO)
                    .passwordHash(passwordEncoder.encode("mantari123"))
                    .createdAt(LocalDateTime.of(2026, 4, 25, 10, 0))
                    .build(),
                Usuario.builder()
                    .nombreCompleto("Jean Pool Jaramillo Quispe")
                    .email("jjaramillo@econexus.com")
                    .telefono("922744558")
                    .rol(RolEnum.SUPERVISOR)
                    .estado(EstadoEnum.ACTIVO)
                    .passwordHash(passwordEncoder.encode("jaramillo123"))
                    .createdAt(LocalDateTime.of(2026, 4, 25, 10, 0))
                    .build(),
                Usuario.builder()
                    .nombreCompleto("Frank Erick Campomanes Vergara")
                    .email("fcampomanes@econexus.com")
                    .telefono("926577602")
                    .rol(RolEnum.OPERADOR)
                    .estado(EstadoEnum.INACTIVO)
                    .passwordHash(passwordEncoder.encode("campomanes123"))
                    .createdAt(LocalDateTime.of(2026, 4, 25, 10, 0))
                    .build()
            );

            usuarioRepository.saveAll(initialUsers);
            System.out.println("Usuarios iniciales del equipo creados exitosamente.");
        }
    }
}
