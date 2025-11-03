package com.br.ssmup.service;

import com.br.ssmup.dto.*;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.exceptions.ResourceNotFoundException;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.LicensaSanitariaMapper;
import com.br.ssmup.repository.EmpresaRepository;
import com.br.ssmup.repository.LicensaSanitariaRepository;
import com.br.ssmup.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final ResponsavelRepository responsavelRepository;
    private final LicensaSanitariaRepository licensaSanitariaRepository;
    private final EmpresaMapper empresaMapper;
    private final LicensaSanitariaMapper licensaMapper;

    public EmpresaService(EmpresaRepository empresaRepository, ResponsavelRepository responsavelRepository, LicensaSanitariaRepository licensaSanitariaRepository, EmpresaMapper empresaMapper, LicensaSanitariaMapper licensaMapper) {
        this.empresaRepository = empresaRepository;
        this.responsavelRepository = responsavelRepository;
        this.licensaSanitariaRepository = licensaSanitariaRepository;
        this.empresaMapper = empresaMapper;
        this.licensaMapper = licensaMapper;
    }

    public EmpresaResponseDto saveEmpresa(EmpresaCadastroDto dto) {
        Empresa empresa = empresaMapper.toEntity(dto);
        responsavelRepository.save(empresa.getResponsavel());
        return empresaMapper.toResponse(empresaRepository.save(empresa));
    }

    public List<EmpresaResponseDto>  listarEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresaMapper::toResponse)
                .toList();
    }

    public EmpresaResponseDto getEmpresaById(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
        return empresaMapper.toResponse(empresa);
    }

    public void  deleteByIdEmpresa(Long id) {
        if(!empresaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empresa não encontrada");
        }
        empresaRepository.deleteById(id);
    }

    public EmpresaAtualizarDto atualizarEmpresa(Long id, EmpresaAtualizarDto dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));

        if(dto.razaoSocial() != null && !dto.razaoSocial().isBlank()) {
            empresa.setRazaoSocial(dto.razaoSocial());
        }

        if(dto.nomeFantasia()  != null &&  !dto.nomeFantasia().isBlank()) {
            empresa.setNomeFantasia(dto.nomeFantasia());
        }

        if (dto.cpfCnpj() != null && !dto.cpfCnpj().isBlank()) {
            empresa.setCpfCnpj(dto.cpfCnpj());
        }

        if(dto.inscricaoEstadual() != null &&  !dto.inscricaoEstadual().isBlank()) {
            empresa.setInscricaoEstadual(dto.inscricaoEstadual());
        }

        if(dto.atividadeFirma() != null &&   !dto.atividadeFirma().isBlank()) {
            empresa.setAtividadeFirma(dto.atividadeFirma());
        }

        if(dto.subAtividade() != null &&   !dto.subAtividade().isBlank()) {
            empresa.setSubAtividade(dto.subAtividade());
        }

        if(dto.dataInicioFuncionamento() != null) {
            empresa.setDataInicioFuncionamento(dto.dataInicioFuncionamento());
        }

        return empresaMapper.toUpdate( empresaRepository.save(empresa));
    }

    public LicensaSanitariaResponseDto saveLicensaSanitaria(Long id, LicensaSanitariaCadastroDto dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        LicensaSanitaria licensaSanitaria = licensaMapper.toEntity(dto);
        licensaSanitaria.setEmpresa(empresa);
        return licensaMapper.toResponse(licensaSanitariaRepository.save(licensaSanitaria));
    }

    public List<LicensaSanitariaResponseDto> listarLicensasSanitarias(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        return empresa.getLicensasSanitarias().stream()
                .map(licensaMapper::toResponse)
                .toList();
    }

}
