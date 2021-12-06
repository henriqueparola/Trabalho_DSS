package com.controllers.PedidosDeOrcamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PedidoDeOrcamentoController {

    @FXML TextField nifInput;
    @FXML TextField equipamentoInput;

    public void addPedidoDeOrcamentoProgramado(ActionEvent event){
        System.out.println(nifInput.getText());
        System.out.println(equipamentoInput.getText());
    }

    public void addPedidoDeOrcamentoExpresso(ActionEvent event){
        System.out.println(nifInput.getText());
        System.out.println(equipamentoInput.getText());
    }
}
