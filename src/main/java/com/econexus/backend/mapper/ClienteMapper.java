package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.ClienteRequest;
import com.econexus.backend.dto.response.ClienteResponse;
import com.econexus.backend.model.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        return ClienteResponse.builder()
                .id(cliente.getId())
                .razon_social(cliente.getRazonSocial())
                .ruc(cliente.getRuc())
                .contacto_principal(cliente.getContactoPrincipal())
                .telefono(cliente.getTelefono())
                .email(cliente.getEmail())
                .direccion(cliente.getDireccion())
                .estado(cliente.getEstado().name())
                .build();
    }

    public Cliente toEntity(ClienteRequest request) {
        if (request == null) {
            return null;
        }

        return Cliente.builder()
                .razonSocial(request.getRazon_social())
                .ruc(request.getRuc())
                .contactoPrincipal(request.getContacto_principal())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .build();
    }
}
