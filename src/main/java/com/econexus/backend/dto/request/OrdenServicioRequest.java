package com.econexus.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenServicioRequest {

    private Long cliente_id;

    private BigDecimal monto_total;

    private String estado_pago;

    private String factura_url;

    private String observaciones;

    private Long tipo_servicio_id;

    private String pdf_base64;
}
