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
                .razonSocial(cliente.getRazonSocial())
                .ruc(cliente.getRuc())
                .contactoPrincipal(cliente.getContactoPrincipal())
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
                .razonSocial(request.getRazonSocial())
                .ruc(request.getRuc())
                .contactoPrincipal(request.getContactoPrincipal())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .build();
    }
    public void updateEntityFromRequest(ClienteRequest request, Cliente entity) {
        if (request == null || entity == null) {
            return;
        }

        if (request.getRazonSocial() != null) {
            entity.setRazonSocial(request.getRazonSocial());
        }
        if (request.getRuc() != null) {
            entity.setRuc(request.getRuc());
        }
        if (request.getContactoPrincipal() != null) {
            entity.setContactoPrincipal(request.getContactoPrincipal());
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
        if (request.getEstado() != null) {
            entity.setEstado(request.getEstado());
        }
    }
}
