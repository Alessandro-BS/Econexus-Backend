package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.ProveedorRequest;
import com.econexus.backend.dto.response.ProveedorResponse;
import com.econexus.backend.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listarProveedores() {
        List<ProveedorResponse> proveedores = proveedorService.listarProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse proveedorCreado = proveedorService.crearProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> editarProveedor(
            @PathVariable Long id, 
            @Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse proveedorActualizado = proveedorService.editarProveedor(id, request);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
