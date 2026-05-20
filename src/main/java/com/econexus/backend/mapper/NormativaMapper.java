package com.econexus.backend.mapper;

import com.econexus.backend.dto.response.NormativaResponse;
import com.econexus.backend.model.entity.Normativa;
import org.springframework.stereotype.Component;

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
                .fecha_publicacion(normativa.getFechaPublicacion() != null ? normativa.getFechaPublicacion().toString() : null)
                .entidad_emisora(normativa.getEntidadEmisora())
                .url_documento(normativa.getUrlDocumento())
                .estado(normativa.getEstado() != null ? normativa.getEstado().name() : null)
                .build();
    }
}
