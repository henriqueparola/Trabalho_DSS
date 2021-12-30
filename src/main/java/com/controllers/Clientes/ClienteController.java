package com.controllers.Clientes;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ClienteController {
    @FXML
    TextField nifInput;
    @FXML
    TextField nomeInput;
    @FXML
    TextField emailInput;
    @FXML
    TextField telemovelInput;

    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    public void addCliente(ActionEvent event){
        if (nifInput.getText() != "" && nomeInput.getText() != "" && emailInput.getText() != "" && telemovelInput.getText() != "") {
            try {
                model.registarCliente(
                        nifInput.getText(),
                        nomeInput.getText(),
                        emailInput.getText(),
                        telemovelInput.getText()
                );
            } catch (ClienteInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("NIF já registado");
                a.show();
                e.printStackTrace();
            }
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
