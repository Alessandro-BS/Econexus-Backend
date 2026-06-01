package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteResponse {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private Long tipoServicioId;
    private String tipoServicioNombre;
    private Long ordenServicioId;
    private String numeroOrden;
    private String descripcion;
    private BigDecimal cantidad;
    private String unidadMedida;
    private String estadoCumplimiento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
