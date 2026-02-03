package com.br.ssmup.mapper;

import com.br.ssmup.dto.UsuarioCadastroDto;
import com.br.ssmup.dto.UsuarioResponseDto;
import com.br.ssmup.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioCadastroDto dto);
    UsuarioResponseDto toResponse(Usuario entity);
}
