package com.econexus.backend.controller.api;

import com.econexus.backend.dto.request.ClienteRequest;
import com.econexus.backend.dto.response.ClienteResponse;
import com.econexus.backend.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse cliente = clienteService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> buscarClientes(@RequestParam(value = "q", required = false) String query) {
        List<ClienteResponse> clientes = clienteService.buscarClientes(query);
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> editarCliente(
            @PathVariable Long id, 
            @Valid @RequestBody ClienteRequest request) {
        ClienteResponse clienteActualizado = clienteService.editarCliente(id, request);
        return ResponseEntity.ok(clienteActualizado);
    }
}
