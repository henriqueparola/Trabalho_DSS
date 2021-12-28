package com.controllers.PedidosDeOrcamento;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidoDeOrcamentoController {
    @FXML TextField nifInput;
    @FXML TextField equipamentoInput;
    @FXML TextField registoBalcaoInput;
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    public void addPedidoDeOrcamentoProgramado(ActionEvent event){
        System.out.println(nifInput.getText());
        System.out.println(equipamentoInput.getText());
        System.out.println(registoBalcaoInput.getText());
        if (nifInput.getText() != "" && equipamentoInput.getText() != "" && registoBalcaoInput.getText() != "") {
            try {
                model.registarPedidoOrcamento(nifInput.getText(), equipamentoInput.getText(), registoBalcaoInput.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (ClienteInvalidoException e) {
                System.out.println("Cliente inválido");
            }
        }
    }

    public void addPedidoDeOrcamentoExpresso(ActionEvent event){
        System.out.println(nifInput.getText());
        System.out.println(equipamentoInput.getText());
    }
}
