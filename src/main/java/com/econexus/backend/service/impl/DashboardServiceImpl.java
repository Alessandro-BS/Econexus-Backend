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
import java.math.RoundingMode;

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

        BigDecimal mesActual = ordenServicioRepository.sumIngresosMesActual();
        BigDecimal mesAnterior = ordenServicioRepository.sumIngresosMesAnterior();
    
        String tendencia = "";
            if (mesAnterior.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal diferencia = mesActual.subtract(mesAnterior);
                BigDecimal porcentaje = diferencia.divide(mesAnterior, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
                tendencia = (porcentaje.signum() >= 0 ? "+" : "") + porcentaje + "%";
            }

            return EstadisticasOrdenesResponse.builder()
                    .ordenesPendientes(pendientes)
                    .ordenesEnProceso(anuladas)
                    .ordenesCompletadas(pagadas)
                    .tendenciaCrecimiento(tendencia)
                    .build();
            }
} 