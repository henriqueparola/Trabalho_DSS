package com.business.Excecoes;

import java.io.Serializable;

public class EquipamentoInvalidoException extends Exception implements Serializable {
    public EquipamentoInvalidoException() {
        super();
    }

    public EquipamentoInvalidoException(String message) {
        super(message);
    }
}
