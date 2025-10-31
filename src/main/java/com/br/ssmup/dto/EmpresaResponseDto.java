package com.br.ssmup.dto;

import java.time.LocalDate;

public record EmpresaResponseDto(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cpfCnpj,
        String inscricaoEstadual,
        String atividadeFirma,
        String subAtividade,
        LocalDate dataInicioFuncionamento,
        boolean ativo,
        EnderecoResponseDto endereco,
        ResponsavelResponseDto responsavel
) {
}
