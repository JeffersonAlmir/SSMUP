package com.br.ssmup.mapper;

import com.br.ssmup.dto.InspecaoRelatorioRequestDto;
import com.br.ssmup.dto.InspecaoRelatorioResponseDto;
import com.br.ssmup.entities.InspecaoRelatorio;
import com.br.ssmup.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InspecaoRelatorioMapper {
    InspecaoRelatorio toEntity(InspecaoRelatorioRequestDto inspecaoRelatorioRequestDto);

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "usuarios", target = "usuariosId", qualifiedByName = "usuariosParaIds")
    InspecaoRelatorioResponseDto toDto(InspecaoRelatorio inspecaoRelatorio);

    @Named("usuariosParaIds")
    default List<Long> usuariosParaIds(List<Usuario> usuarios) {
        if (usuarios == null) {return Collections.emptyList();}
        return usuarios.stream()
                .map(Usuario::getId)
                .collect(Collectors.toList());
    }
}
