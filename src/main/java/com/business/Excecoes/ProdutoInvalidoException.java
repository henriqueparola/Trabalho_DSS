package com.business.Excecoes;

import java.io.Serializable;

public class ProdutoInvalidoException extends Exception implements Serializable {
    public ProdutoInvalidoException() {
    }

    public ProdutoInvalidoException(String message) {
        super(message);
    }
}
