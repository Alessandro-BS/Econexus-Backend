package com.econexus.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProveedorResponse {
    
    private Long id;
    private String razonSocial;
    private String ruc;
    private String contactoPrincipal;
    private String telefono;
    private String email;
    private String direccion;
    
    private Long tipoServicioId;
    private String tipoServicioNombre;
    
    private String estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
