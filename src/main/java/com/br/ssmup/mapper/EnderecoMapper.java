package com.br.ssmup.mapper;

import com.br.ssmup.dto.EnderecoCadastroDto;
import com.br.ssmup.dto.EnderecoResponseDto;
import com.br.ssmup.entities.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    Endereco toEntity(EnderecoCadastroDto dto);
    EnderecoResponseDto toResponse(Endereco endereco);
}
