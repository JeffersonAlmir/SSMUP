package com.br.ssmup.dto;

import jakarta.validation.constraints.NotBlank;

public record HistoricoSituacaoRequestDto(
        @NotBlank(message = "Motivo è obrigatorio para auditoria") String motivo) {
}
