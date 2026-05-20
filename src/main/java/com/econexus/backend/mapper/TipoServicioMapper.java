package com.econexus.backend.mapper;

import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.model.entity.TipoServicio;
import org.springframework.stereotype.Component;

@Component
public class TipoServicioMapper {

    public TipoServicioResponse toResponse(TipoServicio tipoServicio) {
        if (tipoServicio == null) {
            return null;
        }

        return TipoServicioResponse.builder()
                .id(tipoServicio.getId())
                .nombre(tipoServicio.getNombre())
                .categoria(tipoServicio.getCategoria() != null ? tipoServicio.getCategoria().name() : null)
                .descripcion(tipoServicio.getDescripcion())
                .estado(tipoServicio.getEstado() != null ? tipoServicio.getEstado().name() : null)
                .build();
    }
}
