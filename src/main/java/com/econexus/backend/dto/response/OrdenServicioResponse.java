package com.econexus.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenServicioResponse {
    private Long id;
    private String numero_orden;
    private LocalDate fecha_emision;
    private Long cliente_id;
    private String cliente_nombre;
    private BigDecimal monto_total;
    private String estado_pago;
    private String factura_url;
    private String observaciones;
    private Long tipo_servicio_id;
    private String tipo_servicio_nombre;
    private String pdf_base64;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
