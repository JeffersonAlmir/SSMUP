package com.br.ssmup.service;

import com.br.ssmup.dto.*;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.exceptions.ResourceNotFoundException;
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
    private final MapperService mapperService;

    public EmpresaService(EmpresaRepository empresaRepository, ResponsavelRepository responsavelRepository, LicensaSanitariaRepository licensaSanitariaRepository, MapperService mapperService) {
        this.empresaRepository = empresaRepository;
        this.responsavelRepository = responsavelRepository;
        this.licensaSanitariaRepository = licensaSanitariaRepository;
        this.mapperService = mapperService;
    }

    public EmpresaResponseDto saveEmpresa(EmpresaCadastroDto dto) {
        Empresa empresa = mapperService.dtoToEmpresa(dto);
        responsavelRepository.save(empresa.getResponsavel());
        return mapperService.empresaToDto(empresaRepository.save(empresa));
    }

    public List<EmpresaResponseDto>  listarEmpresas() {
        return empresaRepository.findAll().stream()
                .map(mapperService::empresaToDto)
                .toList();
    }

    public EmpresaResponseDto getEmpresaById(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
        return mapperService.empresaToDto(empresa);
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

        return mapperService.empresaToEmpresaAtualizarDto(empresaRepository.save(empresa));
    }

    public LicensaSanitariaResponseDto saveLicensaSanitaria(Long id, LicensaSanitariaCadastroDto dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        LicensaSanitaria newLicensa = new LicensaSanitaria();
        newLicensa.setNumControle(dto.numControle());
        newLicensa.setEmpresa(empresa);
        licensaSanitariaRepository.save(newLicensa);
        return mapperService.licensaToDto(newLicensa);
    }

    public List<LicensaSanitariaResponseDto> listarLicensasSanitarias(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        return empresa.getLicensasSanitarias().stream()
                .map(l -> mapperService.licensaToDto(l))
                .toList();
    }

}
