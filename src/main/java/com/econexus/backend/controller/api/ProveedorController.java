package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.ProveedorRequest;
import com.econexus.backend.dto.response.ProveedorResponse;
import com.econexus.backend.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "Gestión de proveedores y sus datos")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Operation(summary = "Listar proveedores", description = "Obtiene todos los proveedores registrados")
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listarProveedores() {
        List<ProveedorResponse> proveedores = proveedorService.listarProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @Operation(summary = "Crear proveedor", description = "Registra un nuevo proveedor en el sistema")
    @PostMapping
    public ResponseEntity<ProveedorResponse> crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse proveedorCreado = proveedorService.crearProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @Operation(summary = "Editar proveedor", description = "Actualiza los datos de un proveedor existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> editarProveedor(
            @PathVariable Long id, 
            @Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse proveedorActualizado = proveedorService.editarProveedor(id, request);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
