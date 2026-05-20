package com.econexus.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoServicioRequest {

    @NotBlank(message = "El nombre del tipo de servicio es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    @Pattern(regexp = "SOLIDO_PELIGROSO|SOLIDO_NO_PELIGROSO|LIQUIDO|FUMIGACION|DESINFECCION|DESINSECTACION",
             message = "La categoría no es válida")
    private String categoria;

    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    private String descripcion;
}
