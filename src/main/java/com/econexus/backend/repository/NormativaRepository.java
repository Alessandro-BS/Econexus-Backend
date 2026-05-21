package com.econexus.backend.repository;

import com.econexus.backend.model.entity.Normativa;
import com.econexus.backend.model.enums.EstadoNormativaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormativaRepository extends JpaRepository<Normativa, Long> {
    List<Normativa> findAllByEstado(EstadoNormativaEnum estado);
}
