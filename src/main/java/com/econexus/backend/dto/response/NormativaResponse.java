package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NormativaResponse {

    private Long id;
    private String codigo;
    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private String entidadEmisora;
    private String urlDocumento;
    private String estado;
}
