package com.econexus.backend.service;

import com.econexus.backend.dto.request.UsuarioRequest;
import com.econexus.backend.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listarUsuarios();
    UsuarioResponse obtenerUsuarioPorId(Long id);
    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request);
    UsuarioResponse cambiarEstado(Long id);
}
