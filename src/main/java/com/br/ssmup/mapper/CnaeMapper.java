package com.br.ssmup.mapper;

import com.br.ssmup.dto.CnaeResponseDto;

import com.br.ssmup.entities.Cnae;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CnaeMapper {
    CnaeResponseDto toResponse(Cnae cnae);
}
