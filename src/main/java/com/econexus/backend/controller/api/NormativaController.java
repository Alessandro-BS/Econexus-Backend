package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.NormativaRequest;
import com.econexus.backend.dto.response.NormativaResponse;
import com.econexus.backend.service.NormativaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normativas")
@Tag(name = "Normativas", description = "Gestión de normativas de saneamiento ambiental")
@RequiredArgsConstructor
public class NormativaController {

    private final NormativaService normativaService;

    @Operation(summary = "Listar normativas", description = "Obtiene la lista de normativas vigentes")
    @GetMapping
    public ResponseEntity<List<NormativaResponse>> listarNormativasVigentes() {
        List<NormativaResponse> normativas = normativaService.listarNormativasVigentes();
        return ResponseEntity.ok(normativas);
    }

    @Operation(summary = "Crear normativa", description = "Registra una nueva normativa en el sistema")
    @PostMapping
    public ResponseEntity<NormativaResponse> crearNormativa(@Valid @RequestBody NormativaRequest request) {
        NormativaResponse creada = normativaService.crearNormativa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Editar normativa", description = "Actualiza una normativa existente")
    @PutMapping("/{id}")
    public ResponseEntity<NormativaResponse> editarNormativa(
            @PathVariable Long id,
            @Valid @RequestBody NormativaRequest request) {
        NormativaResponse actualizada = normativaService.editarNormativa(id, request);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar normativa", description = "Elimina una normativa por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNormativa(@PathVariable Long id) {
        normativaService.eliminarNormativa(id);
        return ResponseEntity.noContent().build();
    }
}
