package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;
import com.econexus.backend.model.entity.OrdenServicio;
import com.econexus.backend.model.enums.EstadoPagoEnum;
import org.springframework.stereotype.Component;

@Component
public class OrdenServicioMapper {

    public OrdenServicio toEntity(OrdenServicioRequest request) {
        if (request == null) {
            return null;
        }

        OrdenServicio.OrdenServicioBuilder builder = OrdenServicio.builder()
                .montoTotal(request.getMonto_total())
                .facturaUrl(request.getPdf_base64() != null ? request.getPdf_base64() : request.getFactura_url())
                .observaciones(request.getObservaciones());

        if (request.getEstado_pago() != null && !request.getEstado_pago().isBlank()) {
            builder.estadoPago(EstadoPagoEnum.valueOf(request.getEstado_pago().toUpperCase()));
        }

        return builder.build();
    }

    public OrdenServicioResponse toResponse(OrdenServicio ordenServicio) {
        if (ordenServicio == null) {
            return null;
        }

        OrdenServicioResponse.OrdenServicioResponseBuilder response = OrdenServicioResponse.builder()
                .id(ordenServicio.getId())
                .numero_orden(ordenServicio.getNumeroOrden())
                .fecha_emision(ordenServicio.getFechaEmision() != null ? ordenServicio.getFechaEmision().toLocalDate() : null)
                .monto_total(ordenServicio.getMontoTotal())
                .estado_pago(ordenServicio.getEstadoPago() != null ? ordenServicio.getEstadoPago().name() : null)
                .factura_url(ordenServicio.getFacturaUrl())
                .pdf_base64(ordenServicio.getFacturaUrl())
                .observaciones(ordenServicio.getObservaciones())
                .created_at(ordenServicio.getCreatedAt())
                .updated_at(ordenServicio.getUpdatedAt());

        if (ordenServicio.getCliente() != null) {
            response.cliente_id(ordenServicio.getCliente().getId());
            response.cliente_nombre(ordenServicio.getCliente().getRazonSocial());
        }

        if (ordenServicio.getTipoServicio() != null) {
            response.tipo_servicio_id(ordenServicio.getTipoServicio().getId());
            response.tipo_servicio_nombre(ordenServicio.getTipoServicio().getNombre());
        }

        return response.build();
    }

    public void updateEntityFromRequest(OrdenServicioRequest request, OrdenServicio ordenServicio) {
        if (request == null || ordenServicio == null) {
            return;
        }

        if (request.getMonto_total() != null) {
            ordenServicio.setMontoTotal(request.getMonto_total());
        }
        
        if (request.getPdf_base64() != null) {
            ordenServicio.setFacturaUrl(request.getPdf_base64());
        } else if (request.getFactura_url() != null) {
            ordenServicio.setFacturaUrl(request.getFactura_url());
        }
        
        if (request.getObservaciones() != null) {
            ordenServicio.setObservaciones(request.getObservaciones());
        }

        if (request.getEstado_pago() != null && !request.getEstado_pago().isBlank()) {
            try {
                ordenServicio.setEstadoPago(EstadoPagoEnum.valueOf(request.getEstado_pago().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Ignore invalid status
            }
        }
    }
}
