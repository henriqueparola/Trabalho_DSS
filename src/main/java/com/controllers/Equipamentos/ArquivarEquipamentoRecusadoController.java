package com.controllers.Equipamentos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ArquivarEquipamentoRecusadoController {
    @FXML
    TextField codEquipamentoInput;

    @FXML
    void arquivarEquipamentoRecusado(ActionEvent e){
        System.out.println(codEquipamentoInput.getText());
    }
}
