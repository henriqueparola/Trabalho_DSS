package com.controllers.Clientes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClienteController {
    @FXML
    TextField nifInput;
    @FXML
    TextField nomeInput;
    @FXML
    TextField emailInput;
    @FXML
    TextField telemovelInput;

    public void addCliente(ActionEvent event){
        System.out.println(nifInput.getText());
        System.out.println(nomeInput.getText());
        System.out.println(emailInput.getText());
        System.out.println(telemovelInput.getText());
    }
}
