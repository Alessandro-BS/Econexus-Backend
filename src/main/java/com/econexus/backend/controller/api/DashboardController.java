package com.econexus.backend.controller.api;

import com.econexus.backend.dto.response.EstadisticasOrdenesResponse;
import com.econexus.backend.dto.response.KpiResponse;
import com.econexus.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/kpis")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KpiResponse> obtenerKpis() {
        return ResponseEntity.ok(dashboardService.obtenerKpisDelNegocio());
    }

    @GetMapping("/estadisticas-ordenes")
    @PreAuthorize("hasAnyRole('SUPERVISOR', 'ADMIN')")
    public ResponseEntity<EstadisticasOrdenesResponse> obtenerEstadisticas() {
        return ResponseEntity.ok(dashboardService.obtenerEstadisticasTendencias());
    }
}