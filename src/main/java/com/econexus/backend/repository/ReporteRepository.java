package com.econexus.backend.repository;

import com.econexus.backend.model.entity.Reporte;
import com.econexus.backend.model.enums.EstadoCumplimientoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEstadoCumplimiento(EstadoCumplimientoEnum estado);
    List<Reporte> findByClienteId(Long clienteId);
    List<Reporte> findByOrdenServicioId(Long ordenServicioId);
}
