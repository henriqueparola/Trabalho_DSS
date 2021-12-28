package com.business.Excecoes;

public class FuncionarioInvalidoException extends Exception {
    public FuncionarioInvalidoException() {
        super();
    }

    public FuncionarioInvalidoException(String message) {
        super(message);
    }
}
