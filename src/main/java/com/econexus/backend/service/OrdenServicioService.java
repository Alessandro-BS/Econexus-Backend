package com.econexus.backend.service;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;

import java.util.List;

public interface OrdenServicioService {

    List<OrdenServicioResponse> listarOrdenes();

    OrdenServicioResponse crearOrden(OrdenServicioRequest request);

    OrdenServicioResponse editarOrden(Long id, OrdenServicioRequest request);

    void eliminarOrden(Long id);
}
