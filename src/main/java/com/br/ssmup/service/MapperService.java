package com.br.ssmup.service;

import com.br.ssmup.dto.EmpresaAtualizarDto;
import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.EnderecoMapper;
import com.br.ssmup.mapper.LicensaSanitariaMapper;
import com.br.ssmup.mapper.ResponsavelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    private final EmpresaMapper empresaMapper;
    private final EnderecoMapper enderecoMapper;
    private final ResponsavelMapper responsavelMapper;
    private final LicensaSanitariaMapper  licensaSanitariaMapper;

    public MapperService(EmpresaMapper empresaMapper, EnderecoMapper enderecoMapper, ResponsavelMapper responsavelMapper, LicensaSanitariaMapper licensaSanitariaMapper) {
        this.empresaMapper = empresaMapper;
        this.enderecoMapper = enderecoMapper;
        this.responsavelMapper = responsavelMapper;
        this.licensaSanitariaMapper = licensaSanitariaMapper;
    }

    //Utilizado no metodo POST de empresa
    public Empresa dtoToEmpresa(EmpresaCadastroDto dto) {
        Empresa empresa = empresaMapper.empresaCadastroDtoToEmpresa(dto);
        empresa.adicionarEndereco(enderecoMapper.enderecoCadastroDtoToEndereco(dto.enderecoCadastroDto()));
        empresa.adicionarResponsavel(responsavelMapper.responsavelCadastroDtoToResponsavel(dto.responsavelCadastroDto()));
        return empresa;
    }

    //Utilizado no metodo GET de empresa
    public EmpresaResponseDto  empresaToDto(Empresa empresa) {
        return empresaMapper.empresaToEmpresaResponseDto(empresa);
    }

    public EmpresaAtualizarDto empresaToEmpresaAtualizarDto(Empresa empresa) {
        return empresaMapper.empresaToEmpresaAtualizarDto(empresa);
    }

    public LicensaSanitariaResponseDto licensaToDto(LicensaSanitaria licensa) {
        return licensaSanitariaMapper.licensaToLicensaSanitariaResponseDto(licensa);
    }
}
