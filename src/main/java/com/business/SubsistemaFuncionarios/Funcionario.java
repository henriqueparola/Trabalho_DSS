package main.java.com.business.SubsistemaFuncionarios;

public abstract class Funcionario {
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
