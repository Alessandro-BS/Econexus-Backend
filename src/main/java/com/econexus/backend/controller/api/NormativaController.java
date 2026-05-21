package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.NormativaRequest;
import com.econexus.backend.dto.response.NormativaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.econexus.backend.service.NormativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
