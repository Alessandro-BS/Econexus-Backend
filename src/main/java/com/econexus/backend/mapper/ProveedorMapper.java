package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.ProveedorRequest;
import com.econexus.backend.dto.response.ProveedorResponse;
import com.econexus.backend.model.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor toEntity(ProveedorRequest request) {
        if (request == null) {
            return null;
        }

        return Proveedor.builder()
                .razonSocial(request.getRazonSocial())
                .ruc(request.getRuc())
                .contactoPrincipal(request.getContactoPrincipal())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                // El tipoServicio se setea en el Service ya que necesitamos buscarlo por ID
                .build();
    }

    public ProveedorResponse toResponse(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }

        ProveedorResponse.ProveedorResponseBuilder response = ProveedorResponse.builder()
                .id(proveedor.getId())
                .razonSocial(proveedor.getRazonSocial())
                .ruc(proveedor.getRuc())
                .contactoPrincipal(proveedor.getContactoPrincipal())
                .telefono(proveedor.getTelefono())
                .email(proveedor.getEmail())
                .direccion(proveedor.getDireccion())
                .estado(proveedor.getEstado() != null ? proveedor.getEstado().name() : null)
                .createdAt(proveedor.getCreatedAt())
                .updatedAt(proveedor.getUpdatedAt());

        if (proveedor.getTipoServicio() != null) {
            response.tipoServicioId(proveedor.getTipoServicio().getId());
            response.tipoServicioNombre(proveedor.getTipoServicio().getNombre());
        }

        return response.build();
    }

    public void updateEntityFromRequest(ProveedorRequest request, Proveedor proveedor) {
        if (request == null || proveedor == null) {
            return;
        }

        proveedor.setRazonSocial(request.getRazonSocial());
        proveedor.setContactoPrincipal(request.getContactoPrincipal());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setEmail(request.getEmail());
        proveedor.setDireccion(request.getDireccion());
        // El RUC puede o no ser actualizable dependiendo de las reglas, normalmente sí.
        proveedor.setRuc(request.getRuc());
    }
}
