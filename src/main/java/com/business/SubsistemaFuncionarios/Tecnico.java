package com.business.SubsistemaFuncionarios;

import com.business.SubsistemaFuncionarios.Funcionario;

import java.io.Serializable;

public class Tecnico extends Funcionario implements Serializable {
    public Tecnico(String codFunc, String nome) {
        super.setCodFunc(codFunc);
        super.setNome(nome);
    }

    public Funcionario clone() {
        return new Tecnico(super.getCodFunc(), super.getNome());
    }
}
