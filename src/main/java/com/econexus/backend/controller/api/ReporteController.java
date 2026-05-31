package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.ReporteRequest;
import com.econexus.backend.dto.response.ReporteResponse;
import com.econexus.backend.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Gestión de reportes de cumplimiento")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    @Operation(summary = "Listar reportes", description = "Rol mínimo requerido: OPERADOR. Retorna el historial de reportes de servicios.")
    public ResponseEntity<List<ReporteResponse>> listarReportes() {
        List<ReporteResponse> reportes = reporteService.listarReportes();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtrar reportes", description = "Filtra reportes por estado de cumplimiento o por cliente.")
    public ResponseEntity<List<ReporteResponse>> filtrarReportes(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long clienteId) {

        if (estado != null) {
            return ResponseEntity.ok(reporteService.filtrarPorEstado(estado));
        }
        if (clienteId != null) {
            return ResponseEntity.ok(reporteService.filtrarPorCliente(clienteId));
        }
        return ResponseEntity.ok(reporteService.listarReportes());
    }

    @PostMapping
    @Operation(summary = "Crear reporte", description = "Rol mínimo requerido: OPERADOR. Registra el cumplimiento de un servicio.")
    public ResponseEntity<ReporteResponse> crearReporte(@Valid @RequestBody ReporteRequest request) {
        ReporteResponse reporteCreado = reporteService.crearReporte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteCreado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar reporte", description = "Rol mínimo requerido: SUPERVISOR. Actualiza el estado de cumplimiento u otros campos del reporte.")
    public ResponseEntity<ReporteResponse> editarReporte(
            @PathVariable Long id,
            @Valid @RequestBody ReporteRequest request) {
        ReporteResponse reporteActualizado = reporteService.editarReporte(id, request);
        return ResponseEntity.ok(reporteActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reporte", description = "Rol mínimo requerido: ADMIN. Corrige registros erróneos eliminando físicamente el reporte.")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}
