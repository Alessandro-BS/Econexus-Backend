package com.econexus.backend.service.impl;

import com.econexus.backend.dto.response.NormativaResponse;
import com.econexus.backend.mapper.NormativaMapper;
import com.econexus.backend.model.enums.EstadoNormativaEnum;
import com.econexus.backend.repository.NormativaRepository;
import com.econexus.backend.service.NormativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormativaServiceImpl implements NormativaService {

    private final NormativaRepository normativaRepository;
    private final NormativaMapper normativaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<NormativaResponse> listarNormativasVigentes() {
        return normativaRepository.findAllByEstado(EstadoNormativaEnum.VIGENTE)
                .stream()
                .map(normativaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
