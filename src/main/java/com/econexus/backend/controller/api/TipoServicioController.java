package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.TipoServicioRequest;
import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.service.TipoServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-servicio")
@Tag(name = "Tipos de Servicio", description = "Operaciones para gestionar tipos de servicio disponibles")
@RequiredArgsConstructor
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    @Operation(summary = "Listar tipos de servicio", description = "Obtiene los tipos de servicio disponibles")
    @GetMapping
    public ResponseEntity<List<TipoServicioResponse>> listarTiposServicio() {
        List<TipoServicioResponse> servicios = tipoServicioService.listarTiposServicio();
        return ResponseEntity.ok(servicios);
    }

    @Operation(summary = "Crear tipo de servicio", description = "Registra un nuevo tipo de servicio")
    @PostMapping
    public ResponseEntity<TipoServicioResponse> crearTipoServicio(
            @Valid @RequestBody TipoServicioRequest request) {
        TipoServicioResponse servicioCreado = tipoServicioService.crearTipoServicio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCreado);
    }

    @Operation(summary = "Editar tipo de servicio", description = "Actualiza un tipo de servicio existente")
    @PutMapping("/{id}")
    public ResponseEntity<TipoServicioResponse> editarTipoServicio(
            @PathVariable Long id,
            @Valid @RequestBody TipoServicioRequest request) {
        TipoServicioResponse actualizado = tipoServicioService.editarTipoServicio(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar tipo de servicio", description = "Elimina un tipo de servicio por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoServicio(@PathVariable Long id) {
        tipoServicioService.eliminarTipoServicio(id);
        return ResponseEntity.noContent().build();
    }
}
