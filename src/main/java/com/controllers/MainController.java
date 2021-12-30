package com.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private BorderPane mainPane;

    @FXML
    private void clientesAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/clientes/clientes.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void pedidosDeOrcamentoAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/pedidosDeOrcamento/pedidosDeOrcamento.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void orcamentosAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/orcamentos/orcamentos.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void equipamentosAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/equipamentos/equipamentos.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void tecnicoDeReparacoesAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/tecnicos/tecnicoDeReparacoes.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pedidosDeOrcamentoAction(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
