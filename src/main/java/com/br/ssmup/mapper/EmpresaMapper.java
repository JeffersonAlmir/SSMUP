package com.br.ssmup.mapper;

import com.br.ssmup.dto.EmpresaAtualizarDto;
import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.entities.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses =  {EnderecoMapper.class, ResponsavelMapper.class})
public interface EmpresaMapper {

    @Mapping(source = "enderecoCadastroDto", target = "endereco")
    @Mapping(source = "responsavelCadastroDto", target = "responsavel")
    Empresa toEntity(EmpresaCadastroDto dto);

    EmpresaResponseDto toResponse(Empresa empresa);

    EmpresaAtualizarDto toUpdate(Empresa empresa);
}
