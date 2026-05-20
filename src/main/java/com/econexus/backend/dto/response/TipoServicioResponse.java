package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoServicioResponse {
    private Long id;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String estado;
}
