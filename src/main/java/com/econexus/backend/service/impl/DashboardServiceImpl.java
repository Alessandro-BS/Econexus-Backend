package com.econexus.backend.service.impl;

import com.econexus.backend.dto.response.EstadisticasOrdenesResponse;
import com.econexus.backend.dto.response.KpiResponse;
import com.econexus.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    @Override
    public KpiResponse obtenerKpisDelNegocio() {
        return KpiResponse.builder()
                .totalClientesActivos(125)
                .ordenesGeneradasMes(45)
                .ingresosMensuales(new BigDecimal("15400.50"))
                .build();
    }

    @Override
    public EstadisticasOrdenesResponse obtenerEstadisticasTendencias() {
        return EstadisticasOrdenesResponse.builder()
                .ordenesPendientes(12)
                .ordenesEnProceso(8)
                .ordenesCompletadas(25)
                .tendenciaCrecimiento("+15% respecto al mes anterior")
                .build();
    }
}