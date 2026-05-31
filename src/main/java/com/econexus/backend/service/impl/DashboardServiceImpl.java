package com.econexus.backend.service.impl;

import com.econexus.backend.dto.response.EstadisticasOrdenesResponse;
import com.econexus.backend.dto.response.KpiResponse;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.model.enums.EstadoPagoEnum;
import com.econexus.backend.repository.ClienteRepository;
import com.econexus.backend.repository.OrdenServicioRepository;
import com.econexus.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ClienteRepository clienteRepository;
    private final OrdenServicioRepository ordenServicioRepository;

    @Override
    public KpiResponse obtenerKpisDelNegocio() {
        int clientesActivos = (int) clienteRepository.countByEstado(EstadoEnum.ACTIVO);
        int ordenesMes = (int) ordenServicioRepository.countOrdenesMesActual();
        BigDecimal ingresos = ordenServicioRepository.sumIngresosMesActual();

         return KpiResponse.builder()
                .totalClientesActivos(clientesActivos)
                .ordenesGeneradasMes(ordenesMes)
                .ingresosMensuales(ingresos)
                .build();
    }

    @Override
    public EstadisticasOrdenesResponse obtenerEstadisticasTendencias() {
        int pendientes = (int) ordenServicioRepository.countByEstadoPago(EstadoPagoEnum.PENDIENTE);
        int pagadas    = (int) ordenServicioRepository.countByEstadoPago(EstadoPagoEnum.PAGADO);
        int anuladas   = (int) ordenServicioRepository.countByEstadoPago(EstadoPagoEnum.ANULADO);

        return EstadisticasOrdenesResponse.builder()
                .ordenesPendientes(pendientes)
                .ordenesEnProceso(anuladas)
                .ordenesCompletadas(pagadas)
                .tendenciaCrecimiento("")
                .build();
    }
} 