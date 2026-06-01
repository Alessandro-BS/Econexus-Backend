package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.ReporteRequest;
import com.econexus.backend.dto.response.ReporteResponse;
import com.econexus.backend.model.entity.Reporte;
import com.econexus.backend.model.enums.EstadoCumplimientoEnum;
import com.econexus.backend.model.enums.UnidadMedidaEnum;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

    public Reporte toEntity(ReporteRequest request) {
        if (request == null) {
            return null;
        }

        Reporte.ReporteBuilder builder = Reporte.builder()
                .descripcion(request.getDescripcion())
                .cantidad(request.getCantidad());

        if (request.getUnidadMedida() != null && !request.getUnidadMedida().isBlank()) {
            try {
                builder.unidadMedida(UnidadMedidaEnum.valueOf(request.getUnidadMedida().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid enum
            }
        }

        if (request.getEstadoCumplimiento() != null && !request.getEstadoCumplimiento().isBlank()) {
            try {
                builder.estadoCumplimiento(EstadoCumplimientoEnum.valueOf(request.getEstadoCumplimiento().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid enum
            }
        }

        return builder.build();
    }

    public ReporteResponse toResponse(Reporte reporte) {
        if (reporte == null) {
            return null;
        }

        ReporteResponse.ReporteResponseBuilder builder = ReporteResponse.builder()
                .id(reporte.getId())
                .descripcion(reporte.getDescripcion())
                .cantidad(reporte.getCantidad())
                .unidadMedida(reporte.getUnidadMedida() != null ? reporte.getUnidadMedida().name() : null)
                .estadoCumplimiento(reporte.getEstadoCumplimiento() != null ? reporte.getEstadoCumplimiento().name() : null)
                .createdAt(reporte.getCreatedAt())
                .updatedAt(reporte.getUpdatedAt());

        if (reporte.getCliente() != null) {
            builder.clienteId(reporte.getCliente().getId());
            builder.clienteNombre(reporte.getCliente().getRazonSocial());
        }

        if (reporte.getTipoServicio() != null) {
            builder.tipoServicioId(reporte.getTipoServicio().getId());
            builder.tipoServicioNombre(reporte.getTipoServicio().getNombre());
        }

        if (reporte.getOrdenServicio() != null) {
            builder.ordenServicioId(reporte.getOrdenServicio().getId());
            builder.numeroOrden(reporte.getOrdenServicio().getNumeroOrden());
        }

        return builder.build();
    }

    public void updateEntityFromRequest(ReporteRequest request, Reporte reporte) {
        if (request == null || reporte == null) {
            return;
        }

        if (request.getDescripcion() != null) {
            reporte.setDescripcion(request.getDescripcion());
        }

        if (request.getCantidad() != null) {
            reporte.setCantidad(request.getCantidad());
        }

        if (request.getUnidadMedida() != null && !request.getUnidadMedida().isBlank()) {
            try {
                reporte.setUnidadMedida(UnidadMedidaEnum.valueOf(request.getUnidadMedida().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid values
            }
        }

        if (request.getEstadoCumplimiento() != null && !request.getEstadoCumplimiento().isBlank()) {
            try {
                reporte.setEstadoCumplimiento(EstadoCumplimientoEnum.valueOf(request.getEstadoCumplimiento().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid values
            }
        }
    }
}
