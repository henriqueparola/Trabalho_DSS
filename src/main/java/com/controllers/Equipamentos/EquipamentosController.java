package com.controllers.Equipamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaClientes.Cliente;
import com.business.SubsistemaEquipamentos.Equipamento;
import com.controllers.Clientes.ClientesController;
import com.controllers.Global.Modal;
import com.controllers.Orcamentos.GerirPlanoController;
import com.controllers.Orcamentos.OrcamentosController;
import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EquipamentosController implements Initializable {
    @FXML
    TableView table;
    @FXML
    TextField codEquipamentoInput;
    private ObservableList<EquipamentoObs> equipamentos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();
    String stateSelected = "andamento";

    @FXML
    void porPagarAction(ActionEvent e){
        getEquipamentosPorPagar();
        stateSelected = "porPagar";
    }

    @FXML
    void pagoAction(ActionEvent e){
        getEquipamentosPago();
        stateSelected = "pago";
    }

    @FXML
    void recusadoAction(ActionEvent e){
        getEquipamentosRecusados();
        stateSelected = "recusado";
    }

    @FXML
    void emAndamentoAction(ActionEvent e){
        getEquipamentosAndamento();
        stateSelected = "andamento";
    }

    @FXML
    void abandonadoAction(ActionEvent e){
        getEquipamentosAbandonados();
        stateSelected = "abandonado";
    }

    private void getEquipamentosAndamento(){
        equipamentos.removeAll(equipamentos);
        for (String codEquipamento: model.getEquipamentosAndamento()){
            try {
                Equipamento e = model.getEquipamento(codEquipamento);
                equipamentos.add(new EquipamentoObs(
                        e.getCodEquipamento(),
                        e.getNifCliente(),
                        e.getNomeEquipamento()
                ));
            } catch (EquipamentoInvalidoException e) {
                System.out.println("Equipamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getEquipamentosPago(){
        equipamentos.removeAll(equipamentos);
        for (String codEquipamento: model.getEquipamentosPago()){
            try {
                Equipamento e = model.getEquipamento(codEquipamento);
                equipamentos.add(new EquipamentoObs(
                        e.getCodEquipamento(),
                        e.getNifCliente(),
                        e.getNomeEquipamento()
                ));
            } catch (EquipamentoInvalidoException e) {
                System.out.println("Equipamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getEquipamentosPorPagar(){
        equipamentos.removeAll(equipamentos);
        for (String codEquipamento: model.getEquipamentosPorPagar()){
            try {
                Equipamento e = model.getEquipamento(codEquipamento);
                equipamentos.add(new EquipamentoObs(
                        e.getCodEquipamento(),
                        e.getNifCliente(),
                        e.getNomeEquipamento()
                ));
            } catch (EquipamentoInvalidoException e) {
                System.out.println("Equipamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getEquipamentosAbandonados(){
        equipamentos.removeAll(equipamentos);
        for (String codEquipamento: model.getEquipamentosAbandonado()){
            try {
                Equipamento e = model.getEquipamento(codEquipamento);
                equipamentos.add(new EquipamentoObs(
                        e.getCodEquipamento(),
                        e.getNifCliente(),
                        e.getNomeEquipamento()
                ));
            } catch (EquipamentoInvalidoException e) {
                System.out.println("Equipamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getEquipamentosRecusados(){
        equipamentos.removeAll(equipamentos);
        for (String codEquipamento: model.getEquipamentosRecusado()){
            try {
                Equipamento e = model.getEquipamento(codEquipamento);
                equipamentos.add(new EquipamentoObs(
                        e.getCodEquipamento(),
                        e.getNifCliente(),
                        e.getNomeEquipamento()
                ));
            } catch (EquipamentoInvalidoException e) {
                System.out.println("Equipamento inválido");
                //e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<EquipamentoObs,String> codColumn = new TableColumn<>("Código Equipamento");
        TableColumn<EquipamentoObs,String> nifColumn = new TableColumn<>("NIF cliente");
        TableColumn<EquipamentoObs,String> nomeColumn = new TableColumn<>("Equipamento");

        codColumn.setCellValueFactory(new PropertyValueFactory<>("cod"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        codColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        nifColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
        nomeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.35));

        table.getColumns().clear();
        table.getColumns().addAll(
                codColumn,
                nifColumn,
                nomeColumn
        );
        getEquipamentosAndamento();
        table.setItems(equipamentos);

        codEquipamentoInput.setPromptText("Procure por um equipamento");
        codEquipamentoInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == ""){
                    switch (stateSelected){
                        case "andamento":
                            getEquipamentosAndamento();
                            break;
                        case "abandonado":
                            getEquipamentosAbandonados();
                            break;
                        case "porPagar":
                            getEquipamentosPorPagar();
                            break;
                        case "pago":
                            getEquipamentosPago();
                            break;
                        case "recusado":
                            getEquipamentosRecusados();
                            break;
                    }
                }else{
                    try {
                        equipamentos.removeAll(equipamentos);
                        Equipamento e = model.getEquipamento(newValue);
                        equipamentos.add(new EquipamentoObs(
                                e.getCodEquipamento(),
                                e.getNifCliente(),
                                e.getNomeEquipamento()
                        ));
                    }catch (EquipamentoInvalidoException e) {
                        // e.printStackTrace();
                    }
                }
            }
        });
    }

    public class EquipamentoObs {
        private final SimpleStringProperty cod;
        private final SimpleStringProperty nif;
        private final SimpleStringProperty nome;

        public EquipamentoObs(String cod, String nif, String nome) {
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
