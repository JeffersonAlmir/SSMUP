package com.br.ssmup.dto;

import com.br.ssmup.enums.StatusInspecao;

public record EmpresaResponseStatusDto(
        EmpresaResponseDto empresa,
        StatusInspecao status
) {
}
