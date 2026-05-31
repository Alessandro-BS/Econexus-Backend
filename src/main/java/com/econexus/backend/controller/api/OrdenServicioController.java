package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;
import com.econexus.backend.service.OrdenServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-servicio")
@RequiredArgsConstructor
public class OrdenServicioController {

    private final OrdenServicioService ordenServicioService;

    @GetMapping
    public ResponseEntity<List<OrdenServicioResponse>> listarOrdenes() {
        List<OrdenServicioResponse> ordenes = ordenServicioService.listarOrdenes();
        return ResponseEntity.ok(ordenes);
    }

    @PostMapping
    public ResponseEntity<OrdenServicioResponse> crearOrden(@Valid @RequestBody OrdenServicioRequest request) {
        OrdenServicioResponse ordenCreada = ordenServicioService.crearOrden(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenServicioResponse> editarOrden(
            @PathVariable Long id,
            @Valid @RequestBody OrdenServicioRequest request) {
        OrdenServicioResponse ordenActualizada = ordenServicioService.editarOrden(id, request);
        return ResponseEntity.ok(ordenActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenServicioService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
