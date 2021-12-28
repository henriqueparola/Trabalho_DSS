package com.controllers.Global;

import com.controllers.Clientes.ClientesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Modal {
    public static void showModal(String fxmlName,String title){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(ClientesController.class.getResource(fxmlName));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(ClientesController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
