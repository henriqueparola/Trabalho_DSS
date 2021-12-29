package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class OrcamentoFixoConcluidoController implements Initializable {
    String codOrcamento;
    @FXML Text codOrcamentoInput = new Text();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    OrcamentoFixoConcluidoController(String codOrcamento){
        this.codOrcamento = codOrcamento;
    }

    @FXML void registarOrcamentoFixoConluido(ActionEvent e){
        //
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codOrcamentoInput.setText(codOrcamento);
    }
}
