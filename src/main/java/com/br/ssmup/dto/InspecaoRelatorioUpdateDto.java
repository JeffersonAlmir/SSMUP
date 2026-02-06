package com.br.ssmup.dto;

import com.br.ssmup.enums.StatusInspecao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record InspecaoRelatorioUpdateDto(
        String objetivoInspecao,
        String observacoes,
        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInspecao,
        StatusInspecao statusInspecao,
        List<Long> usuariosId
) {
}
