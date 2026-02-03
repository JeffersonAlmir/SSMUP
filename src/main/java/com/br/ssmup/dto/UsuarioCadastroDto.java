package com.br.ssmup.dto;

import com.br.ssmup.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCadastroDto(
        @NotBlank(message = "Nome é obrigatorio")
        String nome,
        @NotBlank(message = "Email é obrigatorio")
        String email,
        @NotBlank(message = "Cargo é obrigatorio")
        String cargo,
        @NotBlank(message = "Matricula é obrigatoria")
        String matricula,
        @NotNull(message = "Role é obrigatoria")
        @Enumerated(EnumType.STRING)
        Role role
) {
}