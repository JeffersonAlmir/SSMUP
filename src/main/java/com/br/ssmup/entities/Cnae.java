package com.br.ssmup.entities;

import com.br.ssmup.enums.RiscoSanitario;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cnae")
public class Cnae {
    @Id
    private String codigo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private RiscoSanitario risco;

    public Cnae(){}

    public Cnae(String codigo, String descricao, RiscoSanitario risco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.risco = risco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RiscoSanitario getRisco() {
        return risco;
    }

    public void setRisco(RiscoSanitario risco) {
        this.risco = risco;
    }
}
