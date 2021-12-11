package com.controllers.Orcamentos;

import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OrcamentosController {

    public void adicionarOrcamentoProgramado(ActionEvent actionEvent) {
        showModal("/view/orcamentos/adicionarOrcamentoProgramado.fxml","Centro de Reparações");
    }

    public void adicionarOrcamentoFixo(ActionEvent actionEvent) {
        showModal("/view/orcamentos/adicionarOrcamentoProgramado.fxml","Centro de Reparações");
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
