package com.br.ssmup.mapper;

import com.br.ssmup.dto.EmpresaAtualizarDto;
import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.entities.Empresa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses =  {EnderecoMapper.class, ResponsavelMapper.class})
public interface EmpresaMapper {

    @Mapping(source = "endereco", target = "endereco")
    @Mapping(source = "responsavel", target = "responsavel")
    Empresa toEntity(EmpresaCadastroDto dto);

    EmpresaResponseDto toResponse(Empresa empresa);

    @AfterMapping
    default void afterMapping(@MappingTarget Empresa empresa) {
        empresa.adicionarEndereco(empresa.getEndereco());
    }

    EmpresaAtualizarDto toUpdate(Empresa empresa);
}
