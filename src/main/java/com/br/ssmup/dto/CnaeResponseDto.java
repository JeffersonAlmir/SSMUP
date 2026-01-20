package com.br.ssmup.dto;

import com.br.ssmup.enums.RiscoSanitario;

public record CnaeResponseDto(
        String codigo,
        String descricao,
        RiscoSanitario risco
) {
}
