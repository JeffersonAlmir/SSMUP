package com.br.ssmup.dto;

import com.br.ssmup.entities.Endereco;
import com.br.ssmup.enums.UnidadeFederativa;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoCadastroDto(
        @NotBlank(message = "Rua é obrigatoria")
        String rua,
        @NotBlank(message = "Numero é obrigatoria, se não houver coloque S/N")
        String numero,
        @NotBlank(message = "Bairro é obrigatorio")
        String bairro,
        @NotBlank(message = "Cep é obrigatorio")
        String cep,
        @NotBlank(message = "Municipio é obrigatorio")
        String municipio,
        @NotNull(message = "Unidade federativa é obrigatoria")
        @Enumerated
        UnidadeFederativa uf,
        @NotBlank(message = "Telefone é obrigatorio")
        String telefone
) {
}
