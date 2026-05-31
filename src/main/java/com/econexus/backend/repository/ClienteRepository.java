package com.econexus.backend.repository;

import com.econexus.backend.model.entity.Cliente;
import com.econexus.backend.model.enums.EstadoEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE (LOWER(c.razonSocial) LIKE LOWER(CONCAT('%', :query, '%')) OR c.ruc LIKE CONCAT('%', :query, '%')) AND c.estado = 'ACTIVO'")
    List<Cliente> buscarClientes(@Param("query") String query);
    long countByEstado(EstadoEnum estado);

boolean existsByRuc(String ruc);
}
