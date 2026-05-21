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
public class NormativaRequest {

    @NotBlank(message = "El código de la normativa es obligatorio")
    @Size(max = 50, message = "El código no puede tener más de 50 caracteres")
    private String codigo;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String titulo;

    @Size(max = 5000, message = "La descripción no puede exceder 5000 caracteres")
    private String descripcion;

    @NotBlank(message = "La fecha de publicación es obligatoria (YYYY-MM-DD)")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener formato YYYY-MM-DD")
    private String fecha_publicacion;

    @NotBlank(message = "La entidad emisora es obligatoria")
    @Size(max = 100, message = "La entidad emisora no puede tener más de 100 caracteres")
    private String entidad_emisora;

    @Size(max = 500, message = "La URL no puede exceder 500 caracteres")
    private String url_documento;

    // opcional: VIGENTE o DEROGADA; si se omite, se asigna VIGENTE en la entidad
    private String estado;
}
