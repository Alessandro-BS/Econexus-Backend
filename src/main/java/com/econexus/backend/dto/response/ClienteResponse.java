package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String razon_social;
    private String ruc;
    private String contacto_principal;
    private String telefono;
    private String email;
    private String direccion;
    private String estado;
}
