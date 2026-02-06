package com.br.ssmup.entities;

import com.br.ssmup.enums.TipoSituacao;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historicos_situacoes")
public class HistoricoSituacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String motivo;

    @Column(nullable = false)
    private TipoSituacao tipoSituacao;

    @CreationTimestamp
    private LocalDateTime data;

    @ManyToOne()
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @ManyToOne()
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public HistoricoSituacao() {}

    public HistoricoSituacao(Long id, String motivo, TipoSituacao tipoSituacao, LocalDateTime data, Empresa empresa, Usuario usuario) {
        this.id = id;
        this.motivo = motivo;
        this.tipoSituacao = tipoSituacao;
        this.data = data;
        this.empresa = empresa;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TipoSituacao getTipoSituacao() {
        return tipoSituacao;
    }

    public void setTipoSituacao(TipoSituacao tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
