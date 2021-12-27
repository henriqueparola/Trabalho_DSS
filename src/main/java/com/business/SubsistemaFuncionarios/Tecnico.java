package main.java.com.business.SubsistemaFuncionarios;

import main.java.com.business.SubsistemaFuncionarios.Funcionario;

public class Tecnico extends Funcionario {
    public Tecnico(String codFunc, String nome) {
        super.setCodFunc(codFunc);
        super.setNome(nome);
    }
}
