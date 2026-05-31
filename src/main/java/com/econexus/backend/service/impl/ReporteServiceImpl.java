package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.ReporteRequest;
import com.econexus.backend.dto.response.ReporteResponse;
import com.econexus.backend.exception.ResourceNotFoundException;
import com.econexus.backend.mapper.ReporteMapper;
import com.econexus.backend.model.entity.Cliente;
import com.econexus.backend.model.entity.OrdenServicio;
import com.econexus.backend.model.entity.Reporte;
import com.econexus.backend.model.entity.TipoServicio;
import com.econexus.backend.model.enums.EstadoCumplimientoEnum;
import com.econexus.backend.repository.ClienteRepository;
import com.econexus.backend.repository.OrdenServicioRepository;
import com.econexus.backend.repository.ReporteRepository;
import com.econexus.backend.repository.TipoServicioRepository;
import com.econexus.backend.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;
    private final ClienteRepository clienteRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final OrdenServicioRepository ordenServicioRepository;
    private final ReporteMapper reporteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponse> listarReportes() {
        return reporteRepository.findAll().stream()
                .map(reporteMapper::toResponse)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponse> filtrarPorEstado(String estadoCumplimiento) {
        EstadoCumplimientoEnum estado = EstadoCumplimientoEnum.valueOf(estadoCumplimiento.toUpperCase());
        return reporteRepository.findByEstadoCumplimiento(estado)
                .stream()
                .map(reporteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponse> filtrarPorCliente(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + clienteId));
        return reporteRepository.findByClienteId(clienteId)
                .stream()
                .map(reporteMapper::toResponse)
                .collect(Collectors.toList());
        }

    @Override
    @Transactional
    public ReporteResponse crearReporte(ReporteRequest request) {
        if (request.getClienteId() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (request.getTipoServicioId() == null) {
            throw new IllegalArgumentException("El tipo de servicio es obligatorio");
        }
        if (request.getCantidad() != null && request.getCantidad().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad del reporte debe ser mayor a cero");
        }


        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + request.getClienteId()));

        TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));

        OrdenServicio ordenServicio = null;
        if (request.getOrdenServicioId() != null) {
            ordenServicio = ordenServicioRepository.findById(request.getOrdenServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró la orden de servicio con ID: " + request.getOrdenServicioId()));
            
            if (ordenServicio.getTipoServicio() == null || 
                !ordenServicio.getTipoServicio().getId().equals(request.getTipoServicioId())) {
                throw new IllegalArgumentException("El tipo de servicio reportado no coincide con el contratado en la orden");
            }
                }

        Reporte reporte = reporteMapper.toEntity(request);
        reporte.setCliente(cliente);
        reporte.setTipoServicio(tipoServicio);
        reporte.setOrdenServicio(ordenServicio);

        Reporte guardado = reporteRepository.save(reporte);
        return reporteMapper.toResponse(guardado);
    }

    @Override
    @Transactional
    public ReporteResponse editarReporte(Long id, ReporteRequest request) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el reporte con ID: " + id));

        if (request.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + request.getClienteId()));
            reporte.setCliente(cliente);
        }

        if (request.getTipoServicioId() != null) {
            TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));
            reporte.setTipoServicio(tipoServicio);
        }

        if (request.getOrdenServicioId() != null) {
            OrdenServicio ordenServicio = ordenServicioRepository.findById(request.getOrdenServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró la orden de servicio con ID: " + request.getOrdenServicioId()));
            reporte.setOrdenServicio(ordenServicio);
        }

        reporteMapper.updateEntityFromRequest(request, reporte);

        Reporte actualizado = reporteRepository.save(reporte);
        return reporteMapper.toResponse(actualizado);
    }

    @Override
    @Transactional
    public void eliminarReporte(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el reporte con ID: " + id));
        reporteRepository.delete(reporte);
    }
}
