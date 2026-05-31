package com.econexus.backend.service.impl;

import com.econexus.backend.dto.request.UsuarioRequest;
import com.econexus.backend.dto.response.UsuarioResponse;
import com.econexus.backend.exception.ResourceNotFoundException;
import com.econexus.backend.mapper.UsuarioMapper;
import com.econexus.backend.model.entity.Usuario;
import com.econexus.backend.model.enums.EstadoEnum;
import com.econexus.backend.repository.UsuarioRepository;
import com.econexus.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioMapper.toResponseList(usuarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(savedUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        // Check email uniqueness if it changed
        if (!usuario.getEmail().equals(request.getEmail()) && 
            usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        usuarioMapper.updateEntityFromRequest(request, usuario);
        
        // Only update password if it's not null and not empty
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(updatedUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponse cambiarEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (usuario.getEstado() == EstadoEnum.ACTIVO) {
            usuario.setEstado(EstadoEnum.INACTIVO);
        } else {
            usuario.setEstado(EstadoEnum.ACTIVO);
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(updatedUsuario);
    }
}
