package com.br.ssmup.mapper;

import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.entities.LicensaSanitaria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LicensaSanitariaMapper {
    LicensaSanitariaResponseDto licensaToLicensaSanitariaResponseDto(LicensaSanitaria licensa);
}
