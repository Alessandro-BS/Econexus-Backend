package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.UsuarioRequest;
import com.econexus.backend.dto.response.UsuarioResponse;
import com.econexus.backend.model.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {
        if (request == null) {
            return null;
        }

        return Usuario.builder()
                .nombreCompleto(request.getNombreCompleto())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .rol(request.getRol())
                .build();
    }

    public UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) {
            return null;
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNombreCompleto(entity.getNombreCompleto());
        response.setEmail(entity.getEmail());
        response.setTelefono(entity.getTelefono());
        response.setRol(entity.getRol());
        response.setEstado(entity.getEstado());
        response.setUltimoLogin(entity.getUltimoLogin());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        
        return response;
    }

    public List<UsuarioResponse> toResponseList(List<Usuario> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntityFromRequest(UsuarioRequest request, Usuario entity) {
        if (request == null || entity == null) {
            return;
        }
        
        if (request.getNombreCompleto() != null) {
            entity.setNombreCompleto(request.getNombreCompleto());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getTelefono() != null) {
            entity.setTelefono(request.getTelefono());
        }
        if (request.getRol() != null) {
            entity.setRol(request.getRol());
        }
        if (request.getEstado() != null) {
            entity.setEstado(request.getEstado());
        }
    }
}
