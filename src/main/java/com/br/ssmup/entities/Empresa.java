package com.br.ssmup.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

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

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(nullable = true, unique = true)
    private String cnpj;

    @Column(name = "inscricao_estadual", unique = true)
    private String inscricaoEstadual;

    @Column(name = "atividade_firma", nullable = false)
    private String atividadeFirma;

    @Column(name = "sub_atividade")
    private String subAtividade;

    @Column(name = "data_inicio_funcionamento",  nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicioFuncionamento;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false)
    private boolean inspecao = false;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cnae_principal_codigo")
    private Cnae cnaePrincipal;

    public Empresa() {
    }

    public Empresa(String razaoSocial, Long id, String nomeFantasia, String cnpj, String inscricaoEstadual, String atividadeFirma, String subAtividade, LocalDate dataInicioFuncionamento, String email, Responsavel responsavel, Endereco endereco, List<LicensaSanitaria> licensasSanitarias, Localizacao localizacao,  Cnae cnaePrincipal) {
        this.razaoSocial = razaoSocial;
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.atividadeFirma = atividadeFirma;
        this.cnaePrincipal = cnaePrincipal;
        this.subAtividade = subAtividade;
        this.dataInicioFuncionamento = dataInicioFuncionamento;
        this.email = email;
        this.responsavel = responsavel;
        this.endereco = endereco;
        this.licensasSanitarias = licensasSanitarias;
        this.localizacao = localizacao;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public boolean isInspecao() {
        return inspecao;
    }

    public void setInspecao(boolean inspecao) {
        this.inspecao = inspecao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LicensaSanitaria> getLicensasSanitarias() {
        return licensasSanitarias;
    }

    public void setLicensasSanitarias(List<LicensaSanitaria> licensasSanitarias) {
        this.licensasSanitarias = licensasSanitarias;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public void adicionarEndereco(Endereco endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setEmpresa(this);
        }
    }

    public Cnae getCnaePrincipal() {
        return cnaePrincipal;
    }

    public void setCnaePrincipal(Cnae cnaePrincipal) {
        this.cnaePrincipal = cnaePrincipal;
    }
}
