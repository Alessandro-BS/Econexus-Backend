package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.LoginRequest;
import com.econexus.backend.dto.response.AuthResponse;
import com.econexus.backend.model.entity.Usuario;
import com.econexus.backend.repository.UsuarioRepository;
import com.econexus.backend.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para autenticación y obtención de tokens JWT")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica al usuario y devuelve un token JWT")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);

            // Actualizar ultimo login
            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElse(null);
            String nombreCompleto = "";
            String rol = "";
            if (usuario != null) {
                usuario.setUltimoLogin(LocalDateTime.now());
                usuarioRepository.save(usuario);
                nombreCompleto = usuario.getNombreCompleto();
                rol = usuario.getRol().name();
            }

            return ResponseEntity.ok(new AuthResponse(jwt, nombreCompleto, rol));
            
        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).build();
        }
    }
}
