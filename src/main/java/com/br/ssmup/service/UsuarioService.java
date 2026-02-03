package com.br.ssmup.service;

import com.br.ssmup.dto.UsuarioCadastroDto;
import com.br.ssmup.dto.UsuarioResponseDto;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.exceptions.ResourceNotFoundException;
import com.br.ssmup.mapper.UsuarioMapper;
import com.br.ssmup.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioResponseDto> listarUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public UsuarioResponseDto salvarUsuario(UsuarioCadastroDto dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void inativarUsuarioById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario com id: " + id + " não econtrado"));
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void ativarUsuarioById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario com id: " + id + " não econtrado"));
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
    }
}
