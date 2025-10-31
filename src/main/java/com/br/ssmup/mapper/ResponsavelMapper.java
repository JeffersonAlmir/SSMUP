package com.br.ssmup.mapper;

import com.br.ssmup.dto.ResponsavelCadastroDto;
import com.br.ssmup.dto.ResponsavelResponseDto;
import com.br.ssmup.entities.Responsavel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {
    Responsavel responsavelCadastroDtoToResponsavel(ResponsavelCadastroDto responsavelCadastroDto);
}
