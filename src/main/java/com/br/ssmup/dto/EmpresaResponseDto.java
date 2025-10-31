package com.br.ssmup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EmpresaResponseDto(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cpfCnpj,
        String inscricaoEstadual,
        String atividadeFirma,
        String subAtividade,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicioFuncionamento,
        boolean ativo,
        EnderecoResponseDto endereco,
        ResponsavelResponseDto responsavel
) {
}
