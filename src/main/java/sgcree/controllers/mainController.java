package sgcree.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import sgcree.App;

import java.io.IOException;

public class mainController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private void clientesAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("clientes.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void pedidosDeOrcamentoAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("pedidosDeOrcamento.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void orcamentosAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("orcamentos.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void equipamentosAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("equipamentos.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

    @FXML
    private void inicioAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
        mainPane.setCenter(fxmlLoader.load());
    }

}
