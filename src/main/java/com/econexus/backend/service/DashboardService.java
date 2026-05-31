package com.econexus.backend.service;

import com.econexus.backend.dto.response.EstadisticasOrdenesResponse;
import com.econexus.backend.dto.response.KpiResponse;

public interface DashboardService {
    KpiResponse obtenerKpisDelNegocio();
    EstadisticasOrdenesResponse obtenerEstadisticasTendencias();
}