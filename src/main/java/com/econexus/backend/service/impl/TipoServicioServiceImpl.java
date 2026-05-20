package com.econexus.backend.service.impl;

import com.econexus.backend.dto.response.TipoServicioResponse;
import com.econexus.backend.mapper.TipoServicioMapper;
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
}
