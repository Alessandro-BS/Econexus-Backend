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
    private Long cliente_id;
    private String cliente_nombre;
    private Long tipo_servicio_id;
    private String tipo_servicio_nombre;
    private Long orden_servicio_id;
    private String numero_orden;
    private String descripcion;
    private BigDecimal cantidad;
    private String unidad_medida;
    private String estado_cumplimiento;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
