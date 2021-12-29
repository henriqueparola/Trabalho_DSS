package com.controllers.PedidosDeOrcamento;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistarPagamentoController {
    @FXML
    TextField codOrcamentoInput;
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    @FXML
    void registarPagamento(ActionEvent event){
        //System.out.println(codOrcamentoInput.getText());
        try {
            model.registarPagamento(codOrcamentoInput.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Pagamento registado com sucesso");
            a.show();
        } catch (OrcamentoInvalidoException ex) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Orçamento não conluído");
            a.show();
            ex.printStackTrace();
        } catch (EquipamentoInvalidoException ex) {
            ex.printStackTrace();
        }
    }
}
