package com.econexus.backend.service;

import com.econexus.backend.dto.response.NormativaResponse;

import java.util.List;

public interface NormativaService {
    List<NormativaResponse> listarNormativasVigentes();
}
