package com.controllers.Orcamentos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerPassoController implements Initializable {
    @FXML
    public Text descricao;
    public String descricaoS;
    @FXML
    public Text precoTotal;
    public String precoTotalS;
    @FXML
    public Text tempoEstimado;
    public String tempoEstimadoS;
    @FXML
    TextField descricaoInput;
    @FXML
    TextField tempoEstimadoInput;
    @FXML
    TextField custoEstimadoInput;
    @FXML
    TableView table;

    private final ObservableList<Passo> data =
            FXCollections.observableArrayList(
                    new Passo("13", "4", "descricao1"),
                    new Passo("56", "9", "descricao2"),
                    new Passo("34", "7", "descricao3"),
                    new Passo("75", "13", "descricao4"),
                    new Passo("43", "3", "descricao5")
            );

    private void showModalWithController(String fxmlName,String title, VerPassoController c){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(OrcamentoCController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(OrcamentoCController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
        }
    }

    public VerPassoController(String descricao, String precoTotal, String tempoEstimado){
        this.descricaoS = descricao;
        this.precoTotalS = precoTotal;
        this.tempoEstimadoS = tempoEstimado;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        descricao.setText(descricaoS);
        precoTotal.setText(precoTotalS);
        tempoEstimado.setText(tempoEstimadoS);

        TableColumn<Passo,String> custoColumn = new TableColumn<>("Custo");
        TableColumn<Passo,String> previsaoDoTempoColumn = new TableColumn<>("Previsão do tempo");
        TableColumn<Passo,Void> passoColumn = new TableColumn<>("Passo");

        passoColumn.setCellFactory(param -> new TableCell<Passo,Void>() {
            private final Button seeButton = new Button("Ver");
            {
                seeButton.setOnAction(event -> {
                    System.out.println(this.getTableRow().getItem().getPrevisaoTempo());
                    VerPassoController c = new VerPassoController(
                            this.getTableRow().getItem().getDescricao(),
                            this.getTableRow().getItem().getCusto(),
                            this.getTableRow().getItem().getPrevisaoTempo()
                    );
                    showModalWithController("/view/orcamentos/verPasso.fxml","Centro de Reparações",c);
                });
            }

            @Override
            protected void updateItem(Void passo, boolean empty) {
                super.updateItem(passo, empty);

                if (empty) {
                    setGraphic(null);
                }else{
                    setGraphic(seeButton);
                }
            }
        });
        custoColumn.setCellValueFactory(new PropertyValueFactory<>("custo"));
        previsaoDoTempoColumn.setCellValueFactory(new PropertyValueFactory<>("previsaoTempo"));

        table.getColumns().clear();
        table.getColumns().addAll(passoColumn,custoColumn,previsaoDoTempoColumn);
        table.setItems(data);
    }

    public class Passo {
        private final SimpleStringProperty custo;
        private final SimpleStringProperty previsaoTempo;
        private final SimpleStringProperty descricao;

        private Passo(String fName, String lName, String descricao) {
            this.custo = new SimpleStringProperty(fName);
            this.previsaoTempo = new SimpleStringProperty(lName);
            this.descricao = new SimpleStringProperty(descricao);
        }

        public String getCusto() {
            return custo.get();
        }

        public SimpleStringProperty custoProperty() {
            return custo;
        }

        public void setCusto(String custo) {
            this.custo.set(custo);
        }

        public String getPrevisaoTempo() {
            return previsaoTempo.get();
        }

        public SimpleStringProperty previsaoTempoProperty() {
            return previsaoTempo;
        }

        public void setPrevisaoTempo(String previsaoTempo) {
            this.previsaoTempo.set(previsaoTempo);
        }

        public String getDescricao() {
            return descricao.get();
        }

        public SimpleStringProperty descricaoProperty() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao.set(descricao);
        }
    }

    @FXML
    void inserirPassoAction(ActionEvent e){
        System.out.println(custoEstimadoInput.getText() + "-" + tempoEstimadoInput.getText() + "-" + descricaoInput.getText());
    }
}
