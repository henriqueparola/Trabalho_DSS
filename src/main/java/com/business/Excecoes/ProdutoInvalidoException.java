package com.business.Excecoes;

public class ProdutoInvalidoException extends Exception {
    public ProdutoInvalidoException() {
    }

    public ProdutoInvalidoException(String message) {
        super(message);
    }
}
