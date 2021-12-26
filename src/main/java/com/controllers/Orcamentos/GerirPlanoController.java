package com.controllers.Orcamentos;

import javafx.beans.property.SimpleStringProperty;

public class GerirPlanoController {
    String codCliente;

    public GerirPlanoController(SimpleStringProperty codCliente) {
        this.codCliente = codCliente.get();
    }
}
