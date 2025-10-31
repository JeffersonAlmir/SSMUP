package com.br.ssmup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ResponsavelCadastroDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        String rg,
        String escolaridade,
        String formacao,
        String especializacao,
        String registroConselho
) {
}
