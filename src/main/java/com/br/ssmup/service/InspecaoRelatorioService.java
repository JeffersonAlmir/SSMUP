package com.br.ssmup.service;

import com.br.ssmup.dto.InspecaoRelatorioRequestDto;
import com.br.ssmup.dto.InspecaoRelatorioResponseDto;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.InspecaoRelatorio;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.exceptions.ResourceNotFoundException;
import com.br.ssmup.mapper.InspecaoRelatorioMapper;
import com.br.ssmup.repository.EmpresaRepository;
import com.br.ssmup.repository.InspecaoRelatorioRepository;
import com.br.ssmup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspecaoRelatorioService {

    private final InspecaoRelatorioRepository inspecaoRelatorioRepository;
    private final InspecaoRelatorioMapper inspecaoRelatorioMapper;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public InspecaoRelatorioService(InspecaoRelatorioRepository inspecaoRelatorioRepository, InspecaoRelatorioMapper inspecaoRelatorioMapper, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.inspecaoRelatorioRepository = inspecaoRelatorioRepository;
        this.inspecaoRelatorioMapper = inspecaoRelatorioMapper;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<InspecaoRelatorioResponseDto> listarInspecaoRelatorio() {
        return inspecaoRelatorioRepository.findAll().stream()
                .map(inspecaoRelatorioMapper::toDto)
                .toList();
    }

    public List<InspecaoRelatorioResponseDto> listarInspecaoRelatorioByEmpresaId(Long empresaId) {
        empresaRepository.findById(empresaId).orElseThrow(()-> new ResourceNotFoundException("empresa nao encontrada"));
        return inspecaoRelatorioRepository.findAllByEmpresaId(empresaId).stream()
                .map(inspecaoRelatorioMapper::toDto)
                .toList();
    }

    public InspecaoRelatorioResponseDto salvarInspecaoRelatorio(InspecaoRelatorioRequestDto inspecaoRelatorioRequestDto) {

        Empresa empresa = empresaRepository.findById(inspecaoRelatorioRequestDto.empresaId()).orElseThrow(()-> new ResourceNotFoundException("Empresa com id: " + inspecaoRelatorioRequestDto.empresaId() + " não encontrada"));

        List<Usuario> usuarios = usuarioRepository.findAllById(inspecaoRelatorioRequestDto.usuariosId());

        if(usuarios.isEmpty()){
            throw new RuntimeException("Pelo menos um id de usuario deve ser fornecido");
        }

        InspecaoRelatorio inspecaoRelatorio = new InspecaoRelatorio();
        inspecaoRelatorio.setObjetivoInspecao(inspecaoRelatorioRequestDto.objetivoInspecao());
        inspecaoRelatorio.setObservacoes(inspecaoRelatorioRequestDto.observacoes());
        inspecaoRelatorio.setDataInspecao(inspecaoRelatorioRequestDto.dataInspecao());
        inspecaoRelatorio.setStatusInspecao(inspecaoRelatorioRequestDto.statusInspecao());
        inspecaoRelatorio.setEmpresa(empresa);
        inspecaoRelatorio.setUsuarios(usuarios);

        return inspecaoRelatorioMapper.toDto(inspecaoRelatorioRepository.save(inspecaoRelatorio));
    }
}
