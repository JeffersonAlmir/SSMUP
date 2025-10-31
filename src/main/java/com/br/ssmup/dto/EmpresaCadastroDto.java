package com.br.ssmup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmpresaCadastroDto(
        @NotBlank
        String razaoSocial,
        @NotBlank
        String nomeFantasia,
        @NotBlank
        String cpfCnpj,
        String inscricaoEstadual,
        @NotBlank
        String atividadeFirma,
        @NotBlank
        String subAtividade,
        @NotBlank
        @PastOrPresent
        LocalDate dataInicioFuncionamento,
        EnderecoCadastroDto enderecoCadastroDto,
        ResponsavelCadastroDto responsavelCadastroDto
) {
}
