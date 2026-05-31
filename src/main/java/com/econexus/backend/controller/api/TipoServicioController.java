package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.TipoServicioRequest;
import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.service.TipoServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-servicio")
@RequiredArgsConstructor
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    @GetMapping
    public ResponseEntity<List<TipoServicioResponse>> listarTiposServicio() {
        List<TipoServicioResponse> servicios = tipoServicioService.listarTiposServicio();
        return ResponseEntity.ok(servicios);
    }

    @PostMapping
    public ResponseEntity<TipoServicioResponse> crearTipoServicio(
            @Valid @RequestBody TipoServicioRequest request) {
        TipoServicioResponse servicioCreado = tipoServicioService.crearTipoServicio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoServicioResponse> editarTipoServicio(
            @PathVariable Long id,
            @Valid @RequestBody TipoServicioRequest request) {
        TipoServicioResponse actualizado = tipoServicioService.editarTipoServicio(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoServicio(@PathVariable Long id) {
        tipoServicioService.eliminarTipoServicio(id);
        return ResponseEntity.noContent().build();
    }
}
