package com.econexus.backend.repository;

import com.econexus.backend.model.enums.EstadoPagoEnum;
import com.econexus.backend.model.entity.OrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {

    @Query("SELECT MAX(o.numeroOrden) FROM OrdenServicio o WHERE o.numeroOrden LIKE :pattern")
    Optional<String> findMaxNumeroOrdenByPattern(@Param("pattern") String pattern);

    long countByEstadoPago(EstadoPagoEnum estado);

    @Query("SELECT COALESCE(SUM(o.montoTotal), 0) FROM OrdenServicio o " +
        "WHERE MONTH(o.fechaEmision) = MONTH(CURRENT_DATE) " +
        "AND YEAR(o.fechaEmision) = YEAR(CURRENT_DATE)")
    BigDecimal sumIngresosMesActual();

    @Query("SELECT COUNT(o) FROM OrdenServicio o " +
        "WHERE MONTH(o.fechaEmision) = MONTH(CURRENT_DATE) " +
         "AND YEAR(o.fechaEmision) = YEAR(CURRENT_DATE)")
    long countOrdenesMesActual();

    @Query("SELECT COALESCE(SUM(o.montoTotal), 0) FROM OrdenServicio o " +
       "WHERE MONTH(o.fechaEmision) = MONTH(CURRENT_DATE) - 1 " +
       "AND YEAR(o.fechaEmision) = YEAR(CURRENT_DATE)")
    BigDecimal sumIngresosMesAnterior();

    boolean existsByTipoServicioIdAndEstadoPago(Long tipoServicioId, EstadoPagoEnum estadoPago);
    
    List<OrdenServicio> findByEstadoPago(EstadoPagoEnum estadoPago);
    List<OrdenServicio> findByClienteId(Long clienteId);

}
