package com.br.ssmup.service;

import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.entities.Responsavel;
import com.br.ssmup.mapper.EmpresaMapper;
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

    public Empresa findByIdEmpresa(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public void  deleteByIdEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    public LicensaSanitaria saveLicensaSanitaria(Long id, LicensaSanitaria licensa) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if(empresa == null) {
            throw new RuntimeException("Empresa not found");
        }
        licensa.setEmpresa(empresa);
        return licensaSanitariaRepository.save(licensa);
    }

}
