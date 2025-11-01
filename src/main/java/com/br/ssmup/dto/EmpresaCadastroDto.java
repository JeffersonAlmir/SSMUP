package com.br.ssmup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmpresaCadastroDto(
        @NotBlank(message = "Razão social é obrigatoria")
        String razaoSocial,
        @NotBlank(message = "Nome fantasia é obrigatorio")
        String nomeFantasia,
        @NotBlank(message = "Cpf ou Cnpj é obrigatorio")
        String cpfCnpj,
        String inscricaoEstadual,
        @NotBlank(message = "Atividade da firma é obrigatoria")
        String atividadeFirma,
        String subAtividade,
        @NotNull(message = "Data inicio de funcionamento não pode ser nula")
        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicioFuncionamento,
        @Valid
        EnderecoCadastroDto enderecoCadastroDto,
        @Valid
        ResponsavelCadastroDto responsavelCadastroDto
) {
}
