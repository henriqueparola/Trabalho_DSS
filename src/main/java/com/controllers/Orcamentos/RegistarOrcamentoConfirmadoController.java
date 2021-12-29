package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistarOrcamentoConfirmadoController {
    @FXML
    TextField codOrcamentoInput;
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    @FXML
    void registarOrcamentoConfirmado(ActionEvent event){
        if (codOrcamentoInput.getText() != "") {
            try {
                model.registarOrcamentoAndamento(codOrcamentoInput.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (OrcamentoInvalidoException ex) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Orçamento inexistente");
                a.show();
                //System.out.println("Orçamento inválido");
                //ex.printStackTrace();
            }
        }
    }
}
