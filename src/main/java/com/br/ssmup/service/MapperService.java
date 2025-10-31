package com.br.ssmup.service;

import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.dto.ResponsavelCadastroDto;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.Responsavel;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.EnderecoMapper;
import com.br.ssmup.mapper.ResponsavelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    private final EmpresaMapper empresaMapper;
    private final EnderecoMapper enderecoMapper;
    private final ResponsavelMapper responsavelMapper;

    public MapperService(EmpresaMapper empresaMapper, EnderecoMapper enderecoMapper, ResponsavelMapper responsavelMapper) {
        this.empresaMapper = empresaMapper;
        this.enderecoMapper = enderecoMapper;
        this.responsavelMapper = responsavelMapper;
    }

    //Utilizado no metodo POST
    public Empresa dtoToEmpresa(EmpresaCadastroDto dto) {
        Empresa empresa = empresaMapper.empresaCadastroDtoToEmpresa(dto);
        empresa.adicionarEndereco(enderecoMapper.enderecoCadastroDtoToEndereco(dto.enderecoCadastroDto()));
        empresa.adicionarResponsavel(responsavelMapper.responsavelCadastroDtoToResponsavel(dto.responsavelCadastroDto()));
        return empresa;
    }

    //Utilizado no metodo GET
    public EmpresaResponseDto  empresaToDto(Empresa empresa) {
        return empresaMapper.empresaToEmpresaResponseDto(empresa);
    }


}
