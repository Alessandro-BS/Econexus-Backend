package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;
import com.econexus.backend.service.OrdenServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-servicio")
@Tag(name = "Ordenes de Servicio", description = "Gestión de órdenes de servicio y su estado")
@RequiredArgsConstructor
public class OrdenServicioController {

    private final OrdenServicioService ordenServicioService;

    @Operation(summary = "Listar órdenes de servicio", description = "Obtiene todas las órdenes de servicio registradas")
    @GetMapping
    public ResponseEntity<List<OrdenServicioResponse>> listarOrdenes() {
        List<OrdenServicioResponse> ordenes = ordenServicioService.listarOrdenes();
        return ResponseEntity.ok(ordenes);
    }

    @Operation(summary = "Crear orden de servicio", description = "Crea una nueva orden de servicio")
    @PostMapping
    public ResponseEntity<OrdenServicioResponse> crearOrden(@Valid @RequestBody OrdenServicioRequest request) {
        OrdenServicioResponse ordenCreada = ordenServicioService.crearOrden(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenCreada);
    }

    @Operation(summary = "Editar orden de servicio", description = "Actualiza una orden de servicio existente")
    @PutMapping("/{id}")
    public ResponseEntity<OrdenServicioResponse> editarOrden(
            @PathVariable Long id,
            @Valid @RequestBody OrdenServicioRequest request) {
        OrdenServicioResponse ordenActualizada = ordenServicioService.editarOrden(id, request);
        return ResponseEntity.ok(ordenActualizada);
    }

    @Operation(summary = "Eliminar orden de servicio", description = "Elimina una orden de servicio por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenServicioService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
