package com.econexus.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El número de orden es obligatorio")
    private String numeroOrden;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El tipo de servicio es obligatorio")
    private Long tipoServicioId;

    @NotNull(message = "El monto total es obligatorio")
    private BigDecimal montoTotal;

    private String estadoPago;  // PENDIENTE | PAGADO | ANULADO (default: PENDIENTE)

    @NotBlank(message = "El archivo PDF de la orden es obligatorio")
    private String facturaUrl;

    private String observaciones;
}
