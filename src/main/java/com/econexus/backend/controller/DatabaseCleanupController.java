package com.econexus.backend.controller;

import com.econexus.backend.repository.ClienteRepository;
import com.econexus.backend.repository.OrdenServicioRepository;
import com.econexus.backend.repository.ProveedorRepository;
import com.econexus.backend.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cleanup")
@RequiredArgsConstructor
public class DatabaseCleanupController {

    private final ClienteRepository clienteRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final ProveedorRepository proveedorRepository;
    private final ReporteRepository reporteRepository;

    @DeleteMapping("/wipe-data")
    public ResponseEntity<String> wipeData() {
        // The order of deletion is important to avoid foreign key constraint violations
        reporteRepository.deleteAll();
        ordenServicioRepository.deleteAll();
        proveedorRepository.deleteAll();
        clienteRepository.deleteAll();
        
        return ResponseEntity.ok("Todos los datos de Clientes, Proveedores, Ventas y Reportes han sido eliminados. Solo se conservan Usuarios, Normativas y Tipos de Servicio.");
    }
}
