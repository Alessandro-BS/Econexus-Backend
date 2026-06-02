package com.econexus.backend.controller.api;

import com.econexus.backend.dto.response.EstadisticasOrdenesResponse;
import com.econexus.backend.dto.response.KpiResponse;
import com.econexus.backend.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Endpoints para métricas y estadísticas del negocio")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Obtener KPIs", description = "Devuelve indicadores clave del negocio")
    @GetMapping("/kpis")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KpiResponse> obtenerKpis() {
        return ResponseEntity.ok(dashboardService.obtenerKpisDelNegocio());
    }

    @Operation(summary = "Obtener estadísticas de órdenes", description = "Devuelve estadísticas y tendencias de órdenes de servicio")
    @GetMapping("/estadisticas-ordenes")
    @PreAuthorize("hasAnyRole('SUPERVISOR', 'ADMIN')")
    public ResponseEntity<EstadisticasOrdenesResponse> obtenerEstadisticas() {
        return ResponseEntity.ok(dashboardService.obtenerEstadisticasTendencias());
    }
}