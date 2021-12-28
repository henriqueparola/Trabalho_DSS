package com.business.Excecoes;

public class PedidoOrcamentoInvalidoException extends Exception {
    public PedidoOrcamentoInvalidoException() {
        super();
    }

    public PedidoOrcamentoInvalidoException(String message) {
        super(message);
    }
}
