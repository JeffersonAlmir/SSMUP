package com.br.ssmup.dto;

import com.br.ssmup.entities.Endereco;
import com.br.ssmup.enums.UnidadeFederativa;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoCadastroDto(
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String bairro,
        @NotBlank
        String cep,
        @NotBlank
        String municipio,
        @NotNull
        @Enumerated
        UnidadeFederativa uf,
        @NotBlank
        String telefone
) {
}
