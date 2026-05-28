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

        if (request.getUnidad_medida() != null && !request.getUnidad_medida().isBlank()) {
            try {
                builder.unidadMedida(UnidadMedidaEnum.valueOf(request.getUnidad_medida().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid enum
            }
        }

        if (request.getEstado_cumplimiento() != null && !request.getEstado_cumplimiento().isBlank()) {
            try {
                builder.estadoCumplimiento(EstadoCumplimientoEnum.valueOf(request.getEstado_cumplimiento().toUpperCase()));
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
                .unidad_medida(reporte.getUnidadMedida() != null ? reporte.getUnidadMedida().name() : null)
                .estado_cumplimiento(reporte.getEstadoCumplimiento() != null ? reporte.getEstadoCumplimiento().name() : null)
                .created_at(reporte.getCreatedAt())
                .updated_at(reporte.getUpdatedAt());

        if (reporte.getCliente() != null) {
            builder.cliente_id(reporte.getCliente().getId());
            builder.cliente_nombre(reporte.getCliente().getRazonSocial());
        }

        if (reporte.getTipoServicio() != null) {
            builder.tipo_servicio_id(reporte.getTipoServicio().getId());
            builder.tipo_servicio_nombre(reporte.getTipoServicio().getNombre());
        }

        if (reporte.getOrdenServicio() != null) {
            builder.orden_servicio_id(reporte.getOrdenServicio().getId());
            builder.numero_orden(reporte.getOrdenServicio().getNumeroOrden());
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

        if (request.getUnidad_medida() != null && !request.getUnidad_medida().isBlank()) {
            try {
                reporte.setUnidadMedida(UnidadMedidaEnum.valueOf(request.getUnidad_medida().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid values
            }
        }

        if (request.getEstado_cumplimiento() != null && !request.getEstado_cumplimiento().isBlank()) {
            try {
                reporte.setEstadoCumplimiento(EstadoCumplimientoEnum.valueOf(request.getEstado_cumplimiento().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid values
            }
        }
    }
}
