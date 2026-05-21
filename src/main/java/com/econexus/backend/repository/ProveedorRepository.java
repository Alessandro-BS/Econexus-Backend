package com.econexus.backend.repository;

import com.econexus.backend.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    boolean existsByRuc(String ruc);
    
    Optional<Proveedor> findByRuc(String ruc);
}
