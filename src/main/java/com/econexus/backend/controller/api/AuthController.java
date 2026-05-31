package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.LoginRequest;
import com.econexus.backend.dto.response.AuthResponse;
import com.econexus.backend.model.entity.Usuario;
import com.econexus.backend.repository.UsuarioRepository;
import com.econexus.backend.security.jwt.JwtTokenProvider;
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
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
        if (usuario != null) {
            usuario.setUltimoLogin(LocalDateTime.now());
            usuarioRepository.save(usuario);
        }

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
