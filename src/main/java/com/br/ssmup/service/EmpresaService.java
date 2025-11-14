package com.br.ssmup.service;

import com.br.ssmup.dto.*;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.entities.Responsavel;
import com.br.ssmup.enums.UnidadeFederativa;
import com.br.ssmup.exceptions.ResourceNotFoundException;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.EnderecoMapper;
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
    private final EnderecoMapper  enderecoMapper;
    private final LicensaSanitariaMapper licensaMapper;

    public EmpresaService(EmpresaRepository empresaRepository, ResponsavelRepository responsavelRepository, LicensaSanitariaRepository licensaSanitariaRepository, EmpresaMapper empresaMapper, EnderecoMapper enderecoMapper,LicensaSanitariaMapper licensaMapper) {
        this.empresaRepository = empresaRepository;
        this.responsavelRepository = responsavelRepository;
        this.licensaSanitariaRepository = licensaSanitariaRepository;
        this.empresaMapper = empresaMapper;
        this.enderecoMapper = enderecoMapper;
        this.licensaMapper = licensaMapper;
    }


    public EmpresaResponseDto saveEmpresa(EmpresaCadastroDto dto) {
        Empresa empresa = empresaMapper.toEntity(dto);
        Responsavel responsavel = responsavelRepository.findByCpf(empresa.getResponsavel().getCpf());
        if(responsavel == null){
            responsavelRepository.save(empresa.getResponsavel());
            return empresaMapper.toResponse(empresaRepository.save(empresa));
        }
        empresa.setResponsavel(responsavel);
        return empresaMapper.toResponse(empresaRepository.save(empresa));
    }

    public LicensaSanitariaResponseDto saveLicensaSanitaria(Long id, LicensaSanitariaCadastroDto dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        LicensaSanitaria licensaSanitaria = licensaMapper.toEntity(dto);
        licensaSanitaria.setEmpresa(empresa);
        return licensaMapper.toResponse(licensaSanitariaRepository.save(licensaSanitaria));
    }

    public List<EmpresaResponseDto>  listarEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresaMapper::toResponse)
                .toList();
    }

    public List<EmpresaResponseDto>  listarEmpresasAtivas() {
        return empresaRepository.findAll().stream()
                .filter(Empresa::isAtivo)
                .map(empresaMapper::toResponse)
                .toList();
    }

    public List<EmpresaResponseDto>  listarEmpresasInativas() {
        return empresaRepository.findAll().stream()
                .filter((empresa) -> !empresa.isAtivo())
                .map(empresaMapper::toResponse)
                .toList();
    }

    public List<LicensaSanitariaResponseDto> listarLicensasSanitarias(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Empresa não encontrada"));
        return empresa.getLicensasSanitarias().stream()
                .map(licensaMapper::toResponse)
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

    public void inativarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
        empresa.setAtivo(false);
        empresaRepository.save(empresa);
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

    public EnderecoResponseDto atualizarEndereco(Long id, EnderecoAtualizarDto dto) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Empresa nao encontrada"));

        if(dto.rua() != null && !dto.rua().isBlank()) {
            empresa.getEndereco().setRua(dto.rua());
        }

        if(dto.numero() != null && !dto.numero().isBlank()) {
            empresa.getEndereco().setNumero(dto.numero());
        }

        if(dto.bairro() != null && !dto.bairro().isBlank()) {
            empresa.getEndereco().setBairro(dto.bairro());
        }

        if(dto.cep() != null && !dto.cep().isBlank()) {
            empresa.getEndereco().setCep(dto.cep());
        }

        if(dto.municipio() != null && !dto.municipio().isBlank()) {
            empresa.getEndereco().setMunicipio(dto.municipio());
        }

        if(dto.uf() != null){
            empresa.getEndereco().setUf(dto.uf());
        }

        if(dto.telefone() != null && !dto.telefone().isBlank()) {
            empresa.getEndereco().setTelefone(dto.telefone());
        }

        empresaRepository.save(empresa);
        return enderecoMapper.toResponse(empresa.getEndereco());
    }



}
