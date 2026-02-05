package com.br.ssmup.dto;

import com.br.ssmup.enums.Role;

public record UsuarioAtualizarDto(
        String nome,
        String email,
        String cargo,
        String matricula,
        Role role
) {
}
