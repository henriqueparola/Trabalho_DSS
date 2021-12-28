package com.controllers.Equipamentos;

import com.controllers.Orcamentos.GerirPlanoController;
import com.controllers.Orcamentos.OrcamentosController;
import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EquipamentosController implements Initializable {
    @FXML
    TableView table;

    private final ObservableList<Equipamento> data =
            FXCollections.observableArrayList(
                    new Equipamento("1","2","bicicleta"),
                    new Equipamento("2","3","iphone"),
                    new Equipamento("3","4","pc")
            );

    @FXML
    void arquivarEquipamentoRecusadoForm(ActionEvent actionEvent){
        showModal("/view/equipamentos/arquivarEquipamentoRecusado.fxml","Centro de Reparações");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Equipamento,String> codColumn = new TableColumn<>("cod");
        TableColumn<Equipamento,String> nifColumn = new TableColumn<>("nif");
        TableColumn<Equipamento,String> nomeColumn = new TableColumn<>("nome");

        codColumn.setCellValueFactory(new PropertyValueFactory<>("cod"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        table.getColumns().clear();
        table.getColumns().addAll(
                codColumn,
                nifColumn,
                nomeColumn
        );
        table.setItems(data);
    }

    public class Equipamento {
        private final SimpleStringProperty cod;
        private final SimpleStringProperty nif;
        private final SimpleStringProperty nome;

        public Equipamento(String cod, String nif, String nome) {
            this.cod = new SimpleStringProperty(cod);
            this.nif = new SimpleStringProperty(nif);
            this.nome = new SimpleStringProperty(nome);
        }

        public String getCod() {
            return cod.get();
        }

        public SimpleStringProperty codProperty() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod.set(cod);
        }

        public String getNif() {
            return nif.get();
        }

        public SimpleStringProperty nifProperty() {
            return nif;
        }

        public void setNif(String nif) {
            this.nif.set(nif);
        }

        public String getNome() {
            return nome.get();
        }

        public SimpleStringProperty nomeProperty() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome.set(nome);
        }
    }
}
