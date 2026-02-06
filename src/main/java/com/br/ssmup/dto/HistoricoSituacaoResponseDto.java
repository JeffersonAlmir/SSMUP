package com.br.ssmup.dto;

import com.br.ssmup.enums.TipoSituacao;

import java.time.LocalDateTime;

public record HistoricoSituacaoResponseDto(
        Long id,
        String motivo,
        TipoSituacao tipoSituacao,
        LocalDateTime data,
        String usuarioResponsavel
) {
}
