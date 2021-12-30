package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OrcamentoFixoConcluidoController implements Initializable {
    String codOrcamento;
    @FXML Text codOrcamentoText = new Text();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    OrcamentoFixoConcluidoController(String codOrcamento){
        this.codOrcamento = codOrcamento;
    }

    @FXML void registarOrcamentoFixoConcluido(ActionEvent event){
        try {
            model.registarOrcamentoExpressoConcluido(codOrcamentoText.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (OrcamentoInvalidoException ex) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Orçamento inexistente");
            a.show();
            ex.printStackTrace();
        } catch (EquipamentoInvalidoException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codOrcamentoText.setText(codOrcamento);
    }
}
