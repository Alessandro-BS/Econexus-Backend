package com.econexus.backend.controller.api;

import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.service.TipoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
