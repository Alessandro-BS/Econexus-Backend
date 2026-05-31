package com.econexus.backend.repository;

import com.econexus.backend.model.entity.OrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {

    @Query("SELECT MAX(o.numeroOrden) FROM OrdenServicio o WHERE o.numeroOrden LIKE :pattern")
    Optional<String> findMaxNumeroOrdenByPattern(@Param("pattern") String pattern);
}
