package com.br.ssmup.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_licensas_sanitarias")
public class LicensaSanitaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_controle", nullable = false)
    private String numControle;

    @Column(name = "data_emissao", updatable = false, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEmissao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa")
    @JsonBackReference
    private Empresa empresa;

    public LicensaSanitaria() {}

    public LicensaSanitaria(Long id, String numControle, LocalDateTime dataEmissao, LocalDate dataValidade, boolean status, Empresa empresa) {
        this.id = id;
        this.numControle = numControle;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.status = status;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumControle() {
        return numControle;
    }

    public void setNumControle(String numControle) {
        this.numControle = numControle;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @PrePersist
    public void prePersist(){
        this.dataEmissao = LocalDateTime.now();
        this.dataValidade = LocalDate.now().plusYears(1);
        this.status = true;
    }

    @PreUpdate
    public void preUpdate(){
        if(LocalDate.now().isAfter(this.dataValidade)){
            this.status = false;
        }
    }
}
