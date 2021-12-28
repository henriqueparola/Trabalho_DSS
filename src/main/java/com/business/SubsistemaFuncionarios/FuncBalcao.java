package com.business.SubsistemaFuncionarios;

public class FuncBalcao extends Funcionario {
    public FuncBalcao(String codFunc, String nome) {
        super.setCodFunc(codFunc);
        super.setNome(nome);
    }

    public Funcionario clone() {
        return new Tecnico(super.getCodFunc(), super.getNome());
    }
}
