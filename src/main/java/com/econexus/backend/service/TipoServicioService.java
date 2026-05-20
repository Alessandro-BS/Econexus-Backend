package com.econexus.backend.service;

import com.econexus.backend.dto.request.TipoServicioRequest;
import com.econexus.backend.dto.response.TipoServicioResponse;

import java.util.List;

public interface TipoServicioService {
    List<TipoServicioResponse> listarTiposServicio();
    TipoServicioResponse crearTipoServicio(TipoServicioRequest request);
}
