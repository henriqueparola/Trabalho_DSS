package com.business.Excecoes;

import java.io.Serializable;

public class PassoInvalidoException extends Exception implements Serializable {
    public PassoInvalidoException() {
        super();
    }

    public PassoInvalidoException(String message) {
        super(message);
    }
}
