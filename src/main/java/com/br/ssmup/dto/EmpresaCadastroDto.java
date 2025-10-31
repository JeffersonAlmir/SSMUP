package com.br.ssmup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicioFuncionamento,
        EnderecoCadastroDto enderecoCadastroDto,
        ResponsavelCadastroDto responsavelCadastroDto
) {
}
