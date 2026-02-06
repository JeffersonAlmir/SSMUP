package com.br.ssmup.mapper;

import com.br.ssmup.dto.HistoricoSituacaoResponseDto;
import com.br.ssmup.entities.HistoricoSituacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoSituacaoMapper {
    @Mapping(source = "usuario.nome", target = "usuarioResponsavel")
    HistoricoSituacaoResponseDto toDto(HistoricoSituacao h);
}
