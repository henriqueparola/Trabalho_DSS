package com.controllers.Orcamentos;

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

public class OrcamentosController implements Initializable {
    @FXML
    TableView table;

    private final ObservableList<Orcamento> data =
            FXCollections.observableArrayList(
                    new Orcamento("1","2","3","4","5"),
                    new Orcamento("2","3","4","5","6"),
                    new Orcamento("3","4","5","6","7"),
                    new Orcamento("4","5","6","7","8"),
                    new Orcamento("5","6","7","8","9"),
                    new Orcamento("6","7","8","9","10"),
                    new Orcamento("7","7","9","10","11")
            );

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Orcamento,String> codTecnicoColumn = new TableColumn<>("Cód Ténico");
        TableColumn<Orcamento,String> codClienteColumn = new TableColumn<>("Cód Cliente");
        TableColumn<Orcamento,String> codEquipamentoColumn = new TableColumn<>("Cód Equipamento");
        TableColumn<Orcamento,String> prazoColumn = new TableColumn<>("Prazo");
        TableColumn<Orcamento,String> precoColumn = new TableColumn<>("Preço");
        TableColumn<Orcamento,Void> planoDeTrabalhoColumn = new TableColumn<>("Plano de Trabalho");

        planoDeTrabalhoColumn.setCellFactory(param -> new TableCell<Orcamento,Void>() {
            private final Button seeButton = new Button("Gerir");
            {
                seeButton.setOnAction(event -> {
                    System.out.println(this.getTableRow().getItem().codCliente);
                    GerirPlanoController c = new GerirPlanoController(
                            this.getTableRow().getItem().codCliente
                    );
                    //showModalWithController("/view/orcamentos/verPasso.fxml","Centro de Reparações",c);
                });
            }

            @Override
            protected void updateItem(Void orcamento, boolean empty) {
                super.updateItem(orcamento, empty);

                if (empty) {
                    setGraphic(null);
                }else{
                    setGraphic(seeButton);
                }
            }
        });
        codTecnicoColumn.setCellValueFactory(new PropertyValueFactory<>("codTecnico"));
        codClienteColumn.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
        codEquipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("codEquipamento"));
        prazoColumn.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        table.getColumns().clear();
        table.getColumns().addAll(
                codTecnicoColumn,
                codClienteColumn,
                codEquipamentoColumn,
                prazoColumn,
                precoColumn,
                planoDeTrabalhoColumn);

        table.setItems(data);
    }

    public class Orcamento {
        private final SimpleStringProperty codTecnico;
        private final SimpleStringProperty codCliente;
        private final SimpleStringProperty codEquipamento;
        private final SimpleStringProperty prazo;
        private final SimpleStringProperty preco;


        public Orcamento(String codTecnico, String codCliente, String codEquipamento, String prazo, String preco) {
            this.codTecnico = new SimpleStringProperty(codTecnico);
            this.codCliente = new SimpleStringProperty(codCliente);
            this.codEquipamento = new SimpleStringProperty(codEquipamento);
            this.prazo = new SimpleStringProperty(prazo);
            this.preco = new SimpleStringProperty(preco);
        }

        public String getCodTecnico() {
            return codTecnico.get();
        }

        public SimpleStringProperty codTecnicoProperty() {
            return codTecnico;
        }

        public void setCodTecnico(String codTecnico) {
            this.codTecnico.set(codTecnico);
        }

        public String getCodCliente() {
            return codCliente.get();
        }

        public SimpleStringProperty codClienteProperty() {
            return codCliente;
        }

        public void setCodCliente(String codCliente) {
            this.codCliente.set(codCliente);
        }

        public String getCodEquipamento() {
            return codEquipamento.get();
        }

        public SimpleStringProperty codEquipamentoProperty() {
            return codEquipamento;
        }

        public void setCodEquipamento(String codEquipamento) {
            this.codEquipamento.set(codEquipamento);
        }

        public String getPrazo() {
            return prazo.get();
        }

        public SimpleStringProperty prazoProperty() {
            return prazo;
        }

        public void setPrazo(String prazo) {
            this.prazo.set(prazo);
        }

        public String getPreco() {
            return preco.get();
        }

        public SimpleStringProperty precoProperty() {
            return preco;
        }

        public void setPreco(String preco) {
            this.preco.set(preco);
        }
    }
}
