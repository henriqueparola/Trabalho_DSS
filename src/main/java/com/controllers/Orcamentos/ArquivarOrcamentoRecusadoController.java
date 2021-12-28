package com.controllers.Orcamentos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ArquivarOrcamentoRecusadoController {
    @FXML
    TextField codOrcamentoInput;

    @FXML
    void registarPagamento(ActionEvent e){
        System.out.println(codOrcamentoInput.getText());
    }
}
