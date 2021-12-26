package com.controllers.Orcamentos;

import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrcamentoController implements Initializable{
    @FXML
    private BorderPane mainPane;
    OrcamentoCController oCController = new OrcamentoCController();
    Node informacoesNode;
    Node planoDeTrabalhoNode;

    @FXML
    private void informacoesAction(ActionEvent event) throws IOException {
        mainPane.setCenter(informacoesNode);
    }

    @FXML
    private void planoDeTrabalhoAction(ActionEvent event) throws IOException {
        mainPane.setCenter(planoDeTrabalhoNode);
    }

    @FXML
    private void finalizarAction(ActionEvent event) throws IOException {
        System.out.println(oCController.nifInput.getText() + " - " +
                oCController.codFuncionarioInput.getText() + " - " +
                oCController.codPedidoDeOrcamentoInput.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader informacoesLoader = new FXMLLoader(OrcamentoController.class.getResource("/view/orcamentos/informacoes.fxml"));
        FXMLLoader planoDeTrabalhoLoader = new FXMLLoader(OrcamentoController.class.getResource("/view/orcamentos/planoDeTrabalho.fxml"));

        informacoesLoader.setController(oCController);
        planoDeTrabalhoLoader.setController(oCController);

        try {
            planoDeTrabalhoNode = planoDeTrabalhoLoader.load();
            informacoesNode = informacoesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            informacoesAction(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
