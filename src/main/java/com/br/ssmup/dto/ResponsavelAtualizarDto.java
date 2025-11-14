package com.br.ssmup.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record ResponsavelAtualizarDto(
        String nome,
        @Email
        String email,
        @CPF
        String cpf,
        String rg,
        String escolaridade,
        String formacao,
        String especializacao,
        String registroConselho
) {
}
