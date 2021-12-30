package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;
import com.business.Excecoes.ProdutoInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrcamentoExpressoController {
    @FXML
    public TextField nifInput = new TextField();
    @FXML
    public TextField codPedidoDeOrcamentoInput = new TextField();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    @FXML void registarOrcamentoExpresso(ActionEvent event){
        try {
            model.registarOrcamentoExpresso(nifInput.getText(),codPedidoDeOrcamentoInput.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (PedidoOrcamentoInvalidoException e) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Pedido de orçamento inválido");
            a.show();
            e.printStackTrace();
        } catch (ProdutoInvalidoException e) {
            e.printStackTrace();
        } catch (EquipamentoInvalidoException e) {
            e.printStackTrace();
        }
    }
}
