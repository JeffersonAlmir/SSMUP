package com.br.ssmup.mapper;

import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.entities.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa empresaCadastroDtoToEmpresa(EmpresaCadastroDto empresaCadastroDto);
    EmpresaResponseDto empresaToEmpresaResponseDto(Empresa empresa);
}
