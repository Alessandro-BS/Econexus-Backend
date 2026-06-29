package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.ProveedorRequest;
import com.econexus.backend.dto.response.ProveedorResponse;
import com.econexus.backend.exception.DuplicateResourceException;
import com.econexus.backend.exception.ResourceNotFoundException;
import com.econexus.backend.mapper.ProveedorMapper;
import com.econexus.backend.model.entity.Proveedor;
import com.econexus.backend.model.entity.TipoServicio;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.repository.ProveedorRepository;
import com.econexus.backend.repository.TipoServicioRepository;
import com.econexus.backend.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final ProveedorMapper proveedorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponse> listarProveedores() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        if (proveedorRepository.existsByRuc(request.getRuc())) {
            throw new DuplicateResourceException("Ya existe un proveedor registrado con el RUC: " + request.getRuc());
        }

        Proveedor proveedor = proveedorMapper.toEntity(request);
        
        if (request.getTipoServicioId() != null) {
            TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));
            proveedor.setTipoServicio(tipoServicio);
        }

        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return proveedorMapper.toResponse(proveedorGuardado);
    }

    @Override
    @Transactional
    public ProveedorResponse editarProveedor(Long id, ProveedorRequest request) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el proveedor con ID: " + id));

        // Validar si el nuevo RUC ya pertenece a otro proveedor
        if (!proveedor.getRuc().equals(request.getRuc()) && proveedorRepository.existsByRuc(request.getRuc())) {
            throw new DuplicateResourceException("Ya existe un proveedor registrado con el RUC: " + request.getRuc());
        }

        proveedorMapper.updateEntityFromRequest(request, proveedor);

        if (request.getTipoServicioId() != null) {
            TipoServicio tipoServicio = tipoServicioRepository.findById(request.getTipoServicioId())
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de servicio con ID: " + request.getTipoServicioId()));
            proveedor.setTipoServicio(tipoServicio);
        } else {
            proveedor.setTipoServicio(null); // Permitir que sea null si lo remueven
        }

        Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
        return proveedorMapper.toResponse(proveedorActualizado);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el proveedor con ID: " + id));

        proveedor.setEstado(EstadoEnum.INACTIVO);
        proveedorRepository.save(proveedor);
    }
}
