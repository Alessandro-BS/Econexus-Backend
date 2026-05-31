package com.econexus.backend.dto.response;

import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.model.enums.RolEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioResponse {
    private Long id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private RolEnum rol;
    private EstadoEnum estado;
    private LocalDateTime ultimoLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
