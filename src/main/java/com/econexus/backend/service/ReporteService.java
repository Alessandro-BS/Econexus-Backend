package com.econexus.backend.service;

import com.econexus.backend.dto.request.ReporteRequest;
import com.econexus.backend.dto.response.ReporteResponse;

import java.util.List;

public interface ReporteService {
    List<ReporteResponse> listarReportes();
    ReporteResponse crearReporte(ReporteRequest request);
    ReporteResponse editarReporte(Long id, ReporteRequest request);
    void eliminarReporte(Long id);
}
