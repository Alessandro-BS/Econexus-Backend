package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.ClienteRequest;
import com.econexus.backend.dto.response.ClienteResponse;
import com.econexus.backend.exception.ResourceNotFoundException;
import com.econexus.backend.mapper.ClienteMapper;
import com.econexus.backend.model.entity.Cliente;
import com.econexus.backend.repository.ClienteRepository;
import com.econexus.backend.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.econexus.backend.exception.DuplicateResourceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest request) {
        if (clienteRepository.existsByRuc(request.getRuc())) {
            throw new DuplicateResourceException("Ya existe un cliente registrado con el RUC: " + request.getRuc());
        }  
        Cliente cliente = clienteMapper.toEntity(request);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> buscarClientes(String query) {
        if (query == null || query.trim().isEmpty()) {
            return listarClientes(); // Si la consulta está vacía, listar todos
        }
        return clienteRepository.buscarClientes(query.trim())
                .stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteResponse editarCliente(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id)); 
        
        // Check uniqueness if ruc changed
        if (!cliente.getRuc().equals(request.getRuc()) && clienteRepository.existsByRuc(request.getRuc())) {
            throw new DuplicateResourceException("Ya existe un cliente registrado con el RUC: " + request.getRuc());
        }

        clienteMapper.updateEntityFromRequest(request, cliente);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponse(cliente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id)); 
        
        // Logical delete (baja lógica) as per constraints
        cliente.setEstado(com.econexus.backend.model.enums.EstadoEnum.INACTIVO);
        clienteRepository.save(cliente);
    }
}
