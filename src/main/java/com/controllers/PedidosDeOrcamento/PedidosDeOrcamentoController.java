package com.controllers.PedidosDeOrcamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PedidosDeOrcamentoController {
    public void adicionarPedidoExpressoForm(ActionEvent actionEvent) {
        showModal("/view/pedidosDeOrcamento/adicionarPedidoExpresso.fxml","Centro de Reparações");
    }

    public void adicionarPedidoProgramadoForm(ActionEvent actionEvent) {
        showModal("/view/pedidosDeOrcamento/adicionarPedidoProgramado.fxml","Centro de Reparações");
    }

    private void showModal(String fxmlName,String title){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(PedidosDeOrcamentoController.class.getResource(fxmlName));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(PedidosDeOrcamentoController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){

        }
    }
}
