package com.econexus.backend.dto.request;

import jakarta.validation.constraints.Min;
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
    private Long clienteId;

    @NotNull(message = "El tipo de servicio es obligatorio")
    private Long tipoServicioId;

    private Long ordenServicioId;
    private String descripcion;

    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidad;
    
    private String unidadMedida;         // KG | LITROS | M2 | UNIDAD
    private String estadoCumplimiento;   // PENDIENTE | EN_PROCESO | CUMPLIDO | OBSERVADO
}
