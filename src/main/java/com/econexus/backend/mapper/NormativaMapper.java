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
                .fecha_publicacion(normativa.getFechaPublicacion() != null ? normativa.getFechaPublicacion().toString() : null)
                .entidad_emisora(normativa.getEntidadEmisora())
                .url_documento(normativa.getUrlDocumento())
                .estado(normativa.getEstado() != null ? normativa.getEstado().name() : null)
                .build();
    }

    public Normativa toEntity(NormativaRequest request) {
        if (request == null) return null;

        Normativa normativa = new Normativa();
        normativa.setCodigo(request.getCodigo().trim());
        normativa.setTitulo(request.getTitulo());
        normativa.setDescripcion(request.getDescripcion());
        // parse fecha_publicacion (expected YYYY-MM-DD)
        if (request.getFecha_publicacion() != null && !request.getFecha_publicacion().isBlank()) {
            normativa.setFechaPublicacion(LocalDate.parse(request.getFecha_publicacion()));
        }
        normativa.setEntidadEmisora(request.getEntidad_emisora());
        normativa.setUrlDocumento(request.getUrl_documento());
        if (request.getEstado() != null) {
            normativa.setEstado(EstadoNormativaEnum.valueOf(request.getEstado().trim().toUpperCase()));
        }
        return normativa;
    }
}
