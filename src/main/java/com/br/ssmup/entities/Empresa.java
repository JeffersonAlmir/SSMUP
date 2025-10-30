package com.br.ssmup.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;

    @Column(name = "inscricao_estadual", unique = true)
    private String inscricaoEstadual;

    @Column(name = "atividade_firma", nullable = false)
    private String atividadeFirma;

    @Column(name = "sub_atividade")
    private String subAtividade;

    @Column(name = "data_inicio_funcionamento",  nullable = false)
    private LocalDate dataInicioFuncionamento;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_responsavel", nullable = false)
    @JsonBackReference
    private Responsavel responsavel;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "empresa")
    @JsonManagedReference
    private List<LicensaSanitaria> licensasSanitarias = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Localizacao localizacao;

    public Empresa() {
    }

    public Empresa(Long id, String razaoSocial, String cpfCnpj, String inscricaoEstadual, String atividadeFirma, String subAtividade, LocalDate dataInicioFuncionamento, boolean ativo, Responsavel responsavel, Endereco endereco, List<LicensaSanitaria> licensasSanitarias) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.atividadeFirma = atividadeFirma;
        this.subAtividade = subAtividade;
        this.dataInicioFuncionamento = dataInicioFuncionamento;
        this.ativo = ativo;
        this.responsavel = responsavel;
        this.endereco = endereco;
        this.licensasSanitarias = licensasSanitarias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getAtividadeFirma() {
        return atividadeFirma;
    }

    public void setAtividadeFirma(String atividadeFirma) {
        this.atividadeFirma = atividadeFirma;
    }

    public String getSubAtividade() {
        return subAtividade;
    }

    public void setSubAtividade(String subAtividade) {
        this.subAtividade = subAtividade;
    }

    public LocalDate getDataInicioFuncionamento() {
        return dataInicioFuncionamento;
    }

    public void setDataInicioFuncionamento(LocalDate dataInicioFuncionamento) {
        this.dataInicioFuncionamento = dataInicioFuncionamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<LicensaSanitaria> getLicensasSanitarias() {
        return licensasSanitarias;
    }

    public void setLicensasSanitarias(List<LicensaSanitaria> licensasSanitarias) {
        this.licensasSanitarias = licensasSanitarias;
    }

    @PrePersist
    public void prePersist(){
        this.ativo = true;
    }
}
