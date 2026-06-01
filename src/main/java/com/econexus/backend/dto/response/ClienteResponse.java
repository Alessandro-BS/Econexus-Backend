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
    private String razonSocial;
    private String ruc;
    private String contactoPrincipal;
    private String telefono;
    private String email;
    private String direccion;
    private String estado;
}
