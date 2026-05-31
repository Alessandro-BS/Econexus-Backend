package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.NormativaRequest;
import com.econexus.backend.dto.response.NormativaResponse;
import com.econexus.backend.service.NormativaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normativas")
@RequiredArgsConstructor
public class NormativaController {

    private final NormativaService normativaService;

    @GetMapping
    public ResponseEntity<List<NormativaResponse>> listarNormativasVigentes() {
        List<NormativaResponse> normativas = normativaService.listarNormativasVigentes();
        return ResponseEntity.ok(normativas);
    }

    @PostMapping
    public ResponseEntity<NormativaResponse> crearNormativa(@Valid @RequestBody NormativaRequest request) {
        NormativaResponse creada = normativaService.crearNormativa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NormativaResponse> editarNormativa(
            @PathVariable Long id,
            @Valid @RequestBody NormativaRequest request) {
        NormativaResponse actualizada = normativaService.editarNormativa(id, request);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNormativa(@PathVariable Long id) {
        normativaService.eliminarNormativa(id);
        return ResponseEntity.noContent().build();
    }
}
