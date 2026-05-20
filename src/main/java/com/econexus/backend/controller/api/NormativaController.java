package com.econexus.backend.controller.api;

import com.econexus.backend.dto.response.NormativaResponse;
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
}
