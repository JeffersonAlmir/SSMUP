package com.br.ssmup.repository;

import com.br.ssmup.entities.HistoricoSituacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoSitucaoRepository extends JpaRepository<HistoricoSituacao, Long> {

    List<HistoricoSituacao> findByEmpresaIdOrderByDataDesc(Long empresaId);

}
