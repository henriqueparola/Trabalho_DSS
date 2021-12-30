package com.business.SubsistemaFuncionarios;

import java.io.Serializable;

public class FuncBalcao extends Funcionario implements Serializable {
    public FuncBalcao(String codFunc, String nome) {
        super.setCodFunc(codFunc);
        super.setNome(nome);
    }

    public Funcionario clone() {
        return new FuncBalcao(super.getCodFunc(), super.getNome());
    }
}
