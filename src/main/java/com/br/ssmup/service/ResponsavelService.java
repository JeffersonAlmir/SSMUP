package com.br.ssmup.service;

import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.dto.ResponsavelCadastroDto;
import com.br.ssmup.dto.ResponsavelResponseDto;
import com.br.ssmup.entities.Responsavel;
import com.br.ssmup.exceptions.ResourceNotFoundException;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.ResponsavelMapper;
import com.br.ssmup.repository.ResponsavelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;
    private final EmpresaMapper empresaMapper;

    public ResponsavelService(ResponsavelRepository responsavelRepository, ResponsavelMapper responsavelMapper, EmpresaMapper empresaMapper) {
        this.responsavelRepository = responsavelRepository;
        this.responsavelMapper = responsavelMapper;
        this.empresaMapper = empresaMapper;
    }

    public List<ResponsavelResponseDto> listarResponsaveis(){
        return responsavelRepository.findAll().stream()
                .map(responsavelMapper::toResponse)
                .toList();
    }

    public List<EmpresaResponseDto>  listarEmpresasResponsaveis(Long id){
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Responsavel nâo encontrado"));
        return responsavel.getEmpresas().stream()
                .map(empresaMapper::toResponse)
                .toList();
    }

    @Transactional
    public ResponsavelResponseDto salvar(ResponsavelCadastroDto dto){
        Responsavel responsavel = responsavelMapper.toEntity(dto);
        responsavelRepository.save(responsavel);
        return responsavelMapper.toResponse(responsavel);
    }

    public ResponsavelResponseDto buscarResponsavelById(Long id){
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Responsavel nâo encontrado"));
        return responsavelMapper.toResponse(responsavel);
    }

    public ResponsavelResponseDto buscarResponsavelByCpf(String cpf){
        Responsavel responsavel = responsavelRepository.findByCpf(cpf).orElseThrow(()->new ResourceNotFoundException("Responsavel nao encontrado para esse CPF"));
        return responsavelMapper.toResponse(responsavel);
    }



}
