package com.br.ssmup.dto;

import com.br.ssmup.entities.Endereco;
import com.br.ssmup.enums.UnidadeFederativa;
import jakarta.validation.constraints.NotBlank;

public record EnderecoCadastroDto(
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String bairro,
        String cep,
        @NotBlank
        String municipio,
        @NotBlank
        UnidadeFederativa uf,
        @NotBlank
        String telefone
) {
}
