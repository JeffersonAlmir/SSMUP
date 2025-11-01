package com.br.ssmup.mapper;

import com.br.ssmup.dto.EmpresaAtualizarDto;
import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.entities.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa empresaCadastroDtoToEmpresa(EmpresaCadastroDto empresaCadastroDto);
    EmpresaAtualizarDto empresaToEmpresaAtualizarDto(Empresa empresa);
    EmpresaResponseDto empresaToEmpresaResponseDto(Empresa empresa);
}
