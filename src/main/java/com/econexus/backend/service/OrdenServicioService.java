package com.econexus.backend.service;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;

import java.util.List;

public interface OrdenServicioService {

    List<OrdenServicioResponse> listarOrdenes();

    List<OrdenServicioResponse> filtrarPorEstadoPago(String estadoPago);
    List<OrdenServicioResponse> filtrarPorCliente(Long clienteId);

    OrdenServicioResponse crearOrden(OrdenServicioRequest request);

    OrdenServicioResponse editarOrden(Long id, OrdenServicioRequest request);

    void eliminarOrden(Long id);
}
