package com.econexus.backend.mapper;

import com.econexus.backend.dto.response.NormativaResponse;
import com.econexus.backend.model.entity.Normativa;
import org.springframework.stereotype.Component;
import com.econexus.backend.dto.request.NormativaRequest;
import com.econexus.backend.model.enums.EstadoNormativaEnum;

import java.time.LocalDate;

@Component
public class NormativaMapper {

    public NormativaResponse toResponse(Normativa normativa) {
        if (normativa == null) {
            return null;
        }

        return NormativaResponse.builder()
                .id(normativa.getId())
                .codigo(normativa.getCodigo())
                .titulo(normativa.getTitulo())
                .descripcion(normativa.getDescripcion())
                .fechaPublicacion(normativa.getFechaPublicacion() != null ? normativa.getFechaPublicacion().toString() : null)
                .entidadEmisora(normativa.getEntidadEmisora())
                .urlDocumento(normativa.getUrlDocumento())
                .estado(normativa.getEstado() != null ? normativa.getEstado().name() : null)
                .build();
    }

    public Normativa toEntity(NormativaRequest request) {
        if (request == null) return null;

        Normativa normativa = new Normativa();
        normativa.setCodigo(request.getCodigo().trim());
        normativa.setTitulo(request.getTitulo());
        normativa.setDescripcion(request.getDescripcion());
        // parse fechaPublicacion (expected YYYY-MM-DD)
        if (request.getFechaPublicacion() != null && !request.getFechaPublicacion().isBlank()) {
            normativa.setFechaPublicacion(LocalDate.parse(request.getFechaPublicacion()));
        }
        normativa.setEntidadEmisora(request.getEntidadEmisora());
        normativa.setUrlDocumento(request.getUrlDocumento());
        if (request.getEstado() != null) {
            normativa.setEstado(EstadoNormativaEnum.valueOf(request.getEstado().trim().toUpperCase()));
        }
        return normativa;
    }

    public void updateEntityFromRequest(NormativaRequest request, Normativa normativa) {
        if (request == null || normativa == null) return;

        if (request.getTitulo() != null) normativa.setTitulo(request.getTitulo());
        if (request.getDescripcion() != null) normativa.setDescripcion(request.getDescripcion());
        if (request.getFechaPublicacion() != null && !request.getFechaPublicacion().isBlank()) {
            normativa.setFechaPublicacion(LocalDate.parse(request.getFechaPublicacion()));
        }
        if (request.getEntidadEmisora() != null) normativa.setEntidadEmisora(request.getEntidadEmisora());
        if (request.getUrlDocumento() != null) normativa.setUrlDocumento(request.getUrlDocumento());
        if (request.getEstado() != null) {
            normativa.setEstado(EstadoNormativaEnum.valueOf(request.getEstado().trim().toUpperCase()));
        }
    }
}
