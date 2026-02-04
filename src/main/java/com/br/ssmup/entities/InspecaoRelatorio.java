package com.br.ssmup.entities;

import com.br.ssmup.enums.StatusInspecao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_inspecoes_relatorios")
public class InspecaoRelatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, name = "objetivo_inspecao")
    private String objetivoInspecao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String observacoes;

    @Column(nullable = false, name = "data_inspecao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInspecao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    StatusInspecao statusInspecao;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "tb_inspecao_usuario",
            joinColumns = @JoinColumn(name = "inspecao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public InspecaoRelatorio() {}

    public InspecaoRelatorio(Long id, String objetivoInspecao, String observacoes, LocalDate dataInspecao, StatusInspecao statusInspecao, Empresa empresa, List<Usuario> usuarios, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.objetivoInspecao = objetivoInspecao;
        this.observacoes = observacoes;
        this.dataInspecao = dataInspecao;
        this.statusInspecao = statusInspecao;
        this.empresa = empresa;
        this.usuarios = usuarios;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjetivoInspecao() {
        return objetivoInspecao;
    }

    public void setObjetivoInspecao(String objetivoInspecao) {
        this.objetivoInspecao = objetivoInspecao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(LocalDate dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public StatusInspecao getStatusInspecao() {
        return statusInspecao;
    }

    public void setStatusInspecao(StatusInspecao statusInspecao) {
        this.statusInspecao = statusInspecao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

}

