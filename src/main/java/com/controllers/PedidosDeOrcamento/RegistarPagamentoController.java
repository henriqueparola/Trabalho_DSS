package com.controllers.PedidosDeOrcamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistarPagamentoController {
    @FXML
    TextField codOrcamentoInput;

    @FXML
    void registarPagamento(ActionEvent e){
        System.out.println(codOrcamentoInput.getText());
    }
}
