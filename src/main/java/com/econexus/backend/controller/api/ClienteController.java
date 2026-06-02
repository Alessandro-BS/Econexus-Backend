package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.ClienteRequest;
import com.econexus.backend.dto.response.ClienteResponse;
import com.econexus.backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones para gestión de clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Listar clientes", description = "Obtiene todos los clientes registrados")
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Crear cliente", description = "Registra un nuevo cliente en el sistema")
    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse cliente = clienteService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Operation(summary = "Buscar clientes", description = "Busca clientes por término o devuelve todos si no se especifica filtro")
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> buscarClientes(@RequestParam(value = "q", required = false) String query) {
        List<ClienteResponse> clientes = clienteService.buscarClientes(query);
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Editar cliente", description = "Actualiza los datos de un cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> editarCliente(
            @PathVariable Long id, 
            @Valid @RequestBody ClienteRequest request) {
        ClienteResponse clienteActualizado = clienteService.editarCliente(id, request);
        return ResponseEntity.ok(clienteActualizado);
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
