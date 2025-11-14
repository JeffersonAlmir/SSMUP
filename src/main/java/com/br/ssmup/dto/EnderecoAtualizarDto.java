package com.br.ssmup.dto;

import com.br.ssmup.enums.UnidadeFederativa;

public record EnderecoAtualizarDto(
        Long id,
        String rua,
        String numero,
        String bairro,
        String cep,
        String municipio,
        UnidadeFederativa uf,
        String telefone
) {

}
