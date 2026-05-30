package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasOrdenesResponse {
    private Integer ordenesPendientes;
    private Integer ordenesEnProceso;
    private Integer ordenesCompletadas;
    private String tendenciaCrecimiento;
}