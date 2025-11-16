package com.br.ssmup.mapper;

import com.br.ssmup.dto.LicensaSanitariaCadastroDto;
import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.entities.LicensaSanitaria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LicensaSanitariaMapper {
    LicensaSanitaria toEntity(LicensaSanitariaCadastroDto dto);
    LicensaSanitariaResponseDto toResponse(LicensaSanitaria entity);
}
