package com;

import com.business.CentroReparacoesLNFacade;
import com.business.ICentroReparacoesLN;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(App.class.getResource("/css/style.css")));
        stage.setTitle("Centro de Reparações");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            try {
                model.saveState();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("FAILED TO SAVE");
            }
        });
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}