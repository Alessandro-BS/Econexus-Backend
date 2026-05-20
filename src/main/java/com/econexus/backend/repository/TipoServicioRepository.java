package com.econexus.backend.repository;

import com.econexus.backend.model.entity.TipoServicio;
import com.econexus.backend.model.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {
    List<TipoServicio> findAllByEstado(EstadoEnum estado);
}
