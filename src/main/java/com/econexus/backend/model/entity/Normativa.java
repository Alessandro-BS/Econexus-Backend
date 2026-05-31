package com.econexus.backend.model.entity;

import com.econexus.backend.model.enums.EstadoNormativaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "normativas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Normativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(name = "entidad_emisora", nullable = false, length = 100)
    private String entidadEmisora;

    @Column(name = "url_documento", length = 500)
    private String urlDocumento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EstadoNormativaEnum estado;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (estado == null) {
            estado = EstadoNormativaEnum.VIGENTE;
        }
    }
}
