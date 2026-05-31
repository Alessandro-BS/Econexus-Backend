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
    public void updateEntityFromRequest(ClienteRequest request, Cliente entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getRazon_social() != null) {
            entity.setRazonSocial(request.getRazon_social());
        }
        if (request.getRuc() != null) {
            entity.setRuc(request.getRuc());
        }
        if (request.getContacto_principal() != null) {
            entity.setContactoPrincipal(request.getContacto_principal());
        }
        if (request.getTelefono() != null) {
            entity.setTelefono(request.getTelefono());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getDireccion() != null) {
            entity.setDireccion(request.getDireccion());
        }
    }
}
