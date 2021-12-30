package com.business.Excecoes;

import java.io.Serializable;

public class FuncionarioInvalidoException extends Exception implements Serializable {
    public FuncionarioInvalidoException() {
        super();
    }

    public FuncionarioInvalidoException(String message) {
        super(message);
    }
}
