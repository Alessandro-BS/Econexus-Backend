package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.TipoServicioRequest;
import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.exception.DuplicateResourceException;
import com.econexus.backend.mapper.TipoServicioMapper;
import com.econexus.backend.model.entity.TipoServicio;
import com.econexus.backend.model.enums.CategoriaTipoServicioEnum;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.repository.TipoServicioRepository;
import com.econexus.backend.service.TipoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;
    private final TipoServicioMapper tipoServicioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicioResponse> listarTiposServicio() {
        return tipoServicioRepository.findAllByEstado(EstadoEnum.ACTIVO)
                .stream()
                .map(tipoServicioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TipoServicioResponse crearTipoServicio(TipoServicioRequest request) {
        String nombreNormalizado = request.getNombre().trim();
        if (tipoServicioRepository.existsByNombreIgnoreCase(nombreNormalizado)) {
            throw new DuplicateResourceException("Tipo de servicio", "nombre", nombreNormalizado);
        }

        TipoServicio tipoServicio = TipoServicio.builder()
                .nombre(nombreNormalizado)
                .categoria(CategoriaTipoServicioEnum.valueOf(request.getCategoria().trim().toUpperCase()))
                .descripcion(request.getDescripcion())
                .estado(EstadoEnum.ACTIVO)
                .build();

        tipoServicio = tipoServicioRepository.save(tipoServicio);
        return tipoServicioMapper.toResponse(tipoServicio);
    }
}
