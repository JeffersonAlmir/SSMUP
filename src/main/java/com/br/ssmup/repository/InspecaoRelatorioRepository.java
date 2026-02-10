package com.br.ssmup.repository;

import com.br.ssmup.entities.InspecaoRelatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InspecaoRelatorioRepository extends JpaRepository<InspecaoRelatorio, Long> {
    List<InspecaoRelatorio> findAllByEmpresaId(Long empresaId);
    Optional<InspecaoRelatorio> findTopByEmpresaIdOrderByCreatedAtDesc(Long empresaId);
}
