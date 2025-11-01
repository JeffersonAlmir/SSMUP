package com.br.ssmup.dto;

import jakarta.validation.constraints.NotBlank;

public record LicensaSanitariaCadastroDto(
        @NotBlank(message = "Número de controle é obrigatorio")
        String numControle
) {
}
