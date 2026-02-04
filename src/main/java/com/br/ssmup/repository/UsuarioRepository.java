package com.br.ssmup.repository;

import com.br.ssmup.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndAtivo(String email, boolean ativo);
    List<Usuario> findByAtivo(Boolean ativo);
}
