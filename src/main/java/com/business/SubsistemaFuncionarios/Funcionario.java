package com.business.SubsistemaFuncionarios;

import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    private String codFunc;
    private String nome;

    public String getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(String codFunc) {
        this.codFunc = codFunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract Funcionario clone();
}
