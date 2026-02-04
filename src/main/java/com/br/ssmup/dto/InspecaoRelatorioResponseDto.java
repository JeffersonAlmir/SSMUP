package com.br.ssmup.dto;

import com.br.ssmup.enums.StatusInspecao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InspecaoRelatorioResponseDto(
        String objetivoInspecao,
        String observacoes,
        LocalDate dataInspecao,
        StatusInspecao statusInspecao,
        Long empresaId,
        List<Long> usuariosId,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
