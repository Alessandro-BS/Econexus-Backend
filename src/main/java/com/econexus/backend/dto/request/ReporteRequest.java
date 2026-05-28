package com.econexus.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteRequest {

    @NotNull(message = "El cliente es obligatorio")
    private Long cliente_id;

    @NotNull(message = "El tipo de servicio es obligatorio")
    private Long tipo_servicio_id;

    private Long orden_servicio_id;
    private String descripcion;
    private BigDecimal cantidad;
    private String unidad_medida;
    private String estado_cumplimiento;
}
