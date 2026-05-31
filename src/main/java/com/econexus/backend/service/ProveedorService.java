package com.econexus.backend.service;

import com.econexus.backend.dto.request.ProveedorRequest;
import com.econexus.backend.dto.response.ProveedorResponse;

import java.util.List;

public interface ProveedorService {
    
    List<ProveedorResponse> listarProveedores();
    
    ProveedorResponse crearProveedor(ProveedorRequest request);
    
    ProveedorResponse editarProveedor(Long id, ProveedorRequest request);
    
    void eliminarProveedor(Long id);
}
