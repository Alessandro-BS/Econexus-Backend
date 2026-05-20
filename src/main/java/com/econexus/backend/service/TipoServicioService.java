package com.econexus.backend.service;

import com.econexus.backend.dto.response.TipoServicioResponse;

import java.util.List;

public interface TipoServicioService {
    List<TipoServicioResponse> listarTiposServicio();
}
