package com.econexus.backend.service;

import com.econexus.backend.dto.request.ClienteRequest;
import com.econexus.backend.dto.response.ClienteResponse;
import java.util.List;

public interface ClienteService {
    List<ClienteResponse> listarClientes();
    ClienteResponse crearCliente(ClienteRequest request);
}
