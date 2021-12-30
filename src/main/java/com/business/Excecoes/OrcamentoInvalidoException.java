package com.business.Excecoes;

import java.io.Serializable;

public class OrcamentoInvalidoException extends Exception implements Serializable {
    public OrcamentoInvalidoException() {
        super();
    }

    public OrcamentoInvalidoException(String message) {
        super(message);
    }
}
