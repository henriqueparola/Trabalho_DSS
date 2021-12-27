package com.business.Excecoes;

public class OrcamentoInvalidoException extends Exception {
    public OrcamentoInvalidoException() {
    }

    public OrcamentoInvalidoException(String message) {
        super(message);
    }
}
