package com.br.ssmup.dto;

public record EmpresaRiscoResponseDto(
        long qtEmpresasBaixoRisco,
        long qtEmpresasRiscoMedio,
        long qtEmpresasRiscoAlto
) {
}
