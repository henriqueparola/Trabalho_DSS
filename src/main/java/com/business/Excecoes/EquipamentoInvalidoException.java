package com.business.Excecoes;

public class EquipamentoInvalidoException extends Exception {
    public EquipamentoInvalidoException() {
        super();
    }

    public EquipamentoInvalidoException(String message) {
        super(message);
    }
}
