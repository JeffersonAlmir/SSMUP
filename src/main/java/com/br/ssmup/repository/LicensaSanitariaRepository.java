package com.br.ssmup.repository;

import com.br.ssmup.entities.LicensaSanitaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicensaSanitariaRepository extends JpaRepository<LicensaSanitaria, Long> {
       Optional <LicensaSanitaria> findByNumControle(String numControle);
       boolean existsByEmpresaIdAndStatusTrue(Long empresaId);
       Optional<LicensaSanitaria> findFirstByEmpresaIdAndStatusTrue(Long empresaId);
}
