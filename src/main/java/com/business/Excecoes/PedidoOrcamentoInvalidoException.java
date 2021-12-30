package com.business.Excecoes;

import java.io.Serializable;

public class PedidoOrcamentoInvalidoException extends Exception implements Serializable {
    public PedidoOrcamentoInvalidoException() {
        super();
    }

    public PedidoOrcamentoInvalidoException(String message) {
        super(message);
    }
}
