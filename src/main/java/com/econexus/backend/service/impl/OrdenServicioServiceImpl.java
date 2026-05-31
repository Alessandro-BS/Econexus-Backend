package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.OrdenServicioRequest;
import com.econexus.backend.dto.response.OrdenServicioResponse;
import com.econexus.backend.exception.ResourceNotFoundException;
import com.econexus.backend.mapper.OrdenServicioMapper;
import com.econexus.backend.model.entity.Cliente;
import com.econexus.backend.model.entity.OrdenServicio;
import com.econexus.backend.model.entity.TipoServicio;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.model.enums.EstadoPagoEnum;
import com.econexus.backend.repository.ClienteRepository;
import com.econexus.backend.repository.OrdenServicioRepository;
import com.econexus.backend.repository.TipoServicioRepository;
import com.econexus.backend.service.OrdenServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServicioServiceImpl implements OrdenServicioService {

    private final OrdenServicioRepository ordenServicioRepository;
    private final ClienteRepository clienteRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final OrdenServicioMapper ordenServicioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenServicioResponse> listarOrdenes() {
        return ordenServicioRepository.findAll().stream()
                .map(ordenServicioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrdenServicioResponse crearOrden(OrdenServicioRequest request) {
        if (request.getClienteId() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (request.getMontoTotal() == null) {
            throw new IllegalArgumentException("El monto total es obligatorio");
        }
        if (request.getMontoTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor a cero");
        }

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + request.getClienteId()));
            if (cliente.getEstado() == EstadoEnum.INACTIVO) {
                throw new IllegalStateException("No se puede crear orden de servicio para un cliente inactivo");
            }
        TipoServicio tipoServicio = null;
        if (request.getTipoServicioId() != null) {
            tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));
        }

        OrdenServicio orden = ordenServicioMapper.toEntity(request);
        orden.setCliente(cliente);
        orden.setTipoServicio(tipoServicio);
        orden.setFechaEmision(LocalDateTime.now());
        orden.setNumeroOrden(request.getNumeroOrden());

        OrdenServicio guardada = ordenServicioRepository.save(orden);
        return ordenServicioMapper.toResponse(guardada);
    }

    @Override
    @Transactional
    public OrdenServicioResponse editarOrden(Long id, OrdenServicioRequest request) {
        OrdenServicio orden = ordenServicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la orden de servicio con ID: " + id));

        if (request.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + request.getClienteId()));
            orden.setCliente(cliente);
        }

        if (request.getTipoServicioId() != null) {
            TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));
            orden.setTipoServicio(tipoServicio);
        }

        ordenServicioMapper.updateEntityFromRequest(request, orden);

        OrdenServicio actualizada = ordenServicioRepository.save(orden);
        return ordenServicioMapper.toResponse(actualizada);
    }

    @Override
    @Transactional
    public void eliminarOrden(Long id) {
        OrdenServicio orden = ordenServicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la orden de servicio con ID: " + id));

        orden.setEstadoPago(EstadoPagoEnum.ANULADO);
        ordenServicioRepository.save(orden);
    }

}
