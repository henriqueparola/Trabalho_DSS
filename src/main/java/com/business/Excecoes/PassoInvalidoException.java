package com.business.Excecoes;

public class PassoInvalidoException extends Exception {
    public PassoInvalidoException() {
        super();
    }

    public PassoInvalidoException(String message) {
        super(message);
    }
}
