package com.econexus.backend.mapper;

import com.econexus.backend.dto.request.UsuarioRequest;
import com.econexus.backend.dto.response.UsuarioResponse;
import com.econexus.backend.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true) // Will be set manually
    @Mapping(target = "estado", ignore = true) // Handled by PrePersist or manually
    @Mapping(target = "ultimoLogin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponseList(List<Usuario> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true) // Only updated if provided separately
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "ultimoLogin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UsuarioRequest request, @MappingTarget Usuario entity);
}
