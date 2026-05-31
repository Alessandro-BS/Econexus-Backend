package com.econexus.backend.service;

import com.econexus.backend.dto.request.NormativaRequest;
import com.econexus.backend.dto.response.NormativaResponse;

import java.util.List;

public interface NormativaService {
    List<NormativaResponse> listarNormativasVigentes();
    NormativaResponse crearNormativa(NormativaRequest request);
    NormativaResponse editarNormativa(Long id, NormativaRequest request);
    void eliminarNormativa(Long id);
}
