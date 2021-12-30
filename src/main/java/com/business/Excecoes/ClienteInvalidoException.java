package com.business.Excecoes;

import java.io.Serializable;

public class ClienteInvalidoException extends Exception implements Serializable {
    public ClienteInvalidoException() {
        super();
    }

    public ClienteInvalidoException(String message) {
        super(message);
    }
}
