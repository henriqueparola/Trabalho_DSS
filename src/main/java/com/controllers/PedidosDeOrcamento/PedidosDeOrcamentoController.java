package com.controllers.PedidosDeOrcamento;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaClientes.Cliente;
import com.business.SubsistemaOrcamentos.PedidoOrcamento;
import com.business.SubsistemaOrcamentos.PedidoOrcamentoFixo;
import com.controllers.Clientes.ClientesController;
import com.controllers.Global.Modal;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PedidosDeOrcamentoController implements Initializable {
    @FXML
    TableView table;
    @FXML
    TextField codPedidoInput;
    private ObservableList<PedidoOrcamentoObs> todosPedidos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    public void adicionarPedidoExpressoForm(ActionEvent actionEvent) {
        Modal.showModal("/view/pedidosDeOrcamento/adicionarPedidoExpresso.fxml", "Centro de Reparações");
        getTodosPedidos();
    }

    public void adicionarPedidoProgramadoForm(ActionEvent actionEvent) {
        Modal.showModal("/view/pedidosDeOrcamento/adicionarPedidoProgramado.fxml", "Centro de Reparações");
        getTodosPedidos();
    }

    @FXML
    void registarPagamentoForm(ActionEvent actionEvent) {
        Modal.showModal("/view/pedidosDeOrcamento/registarPagamento.fxml", "Centro de Reparações");
    }

    private void getTodosPedidos(){
        todosPedidos.removeAll(todosPedidos);
        for (String codPedido: model.getPedidosOrcamento()){
            try {
                PedidoOrcamento p = model.getPedidoOrcamento(codPedido);
                if (p instanceof PedidoOrcamentoFixo){
                    todosPedidos.add(new PedidoOrcamentoObs(
                            p.getCodPedidoOrcamento(),
                            p.getData().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                            p.getRegistoBalcao(),
                            p.getCodEquipamento(),
                            p.getCodCliente(),
                            "expresso"
                    ));
                }else{
                    todosPedidos.add(new PedidoOrcamentoObs(
                            p.getCodPedidoOrcamento(),
                            p.getData().toString(),
                            p.getRegistoBalcao(),
                            p.getCodEquipamento(),
                            p.getCodCliente(),
                            "programado"
                    ));
                }
            } catch (PedidoOrcamentoInvalidoException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<PedidoOrcamentoObs, String> codPedidoColumn = new TableColumn<>("Código Pedido");
        TableColumn<PedidoOrcamentoObs, String> dataColumn = new TableColumn<>("Data de criação");
        TableColumn<PedidoOrcamentoObs, String> tipoPedidoColumn = new TableColumn<>("Tipo");
        TableColumn<PedidoOrcamentoObs, String> registoBalcaoColumn = new TableColumn<>("Registo do Balcão");
        TableColumn<PedidoOrcamentoObs, String> clienteColumn = new TableColumn<>("Cliente");
        TableColumn<PedidoOrcamentoObs, String> equipamentoColumn = new TableColumn<>("Equipamento");

        codPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("codPedidoOrcamento"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        tipoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        registoBalcaoColumn.setCellValueFactory(new PropertyValueFactory<>("registoBalcao"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
        equipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("codEquipamento"));

        codPedidoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        dataColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        tipoPedidoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        registoBalcaoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        clienteColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        equipamentoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));

        table.getColumns().clear();
        table.getColumns().addAll(
                codPedidoColumn,
                dataColumn,
                tipoPedidoColumn,
                registoBalcaoColumn,
                clienteColumn,
                equipamentoColumn
        );

        getTodosPedidos();
        table.setItems(todosPedidos);

        codPedidoInput.setPromptText("Procure por um pedido");
        codPedidoInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == ""){
                    getTodosPedidos();
                }else{
                    try {
                        todosPedidos.removeAll(todosPedidos);
                        PedidoOrcamento p = model.getPedidoOrcamento(newValue);
                        if (p instanceof PedidoOrcamentoFixo){
                            todosPedidos.add(new PedidoOrcamentoObs(
                                    p.getCodPedidoOrcamento(),
                                    p.getData().toString(),
                                    p.getRegistoBalcao(),
                                    p.getCodEquipamento(),
                                    p.getCodCliente(),
                                    "expresso"
                            ));
                        }else{
                            todosPedidos.add(new PedidoOrcamentoObs(
                                    p.getCodPedidoOrcamento(),
                                    p.getData().toString(),
                                    p.getRegistoBalcao(),
                                    p.getCodEquipamento(),
                                    p.getCodCliente(),
                                    "programado"
                            ));

                        }
                    } catch (PedidoOrcamentoInvalidoException e) {
                        // e.printStackTrace();
                    }
                }
            }
        });

    }
    public class PedidoOrcamentoObs {
        private final SimpleStringProperty codPedidoOrcamento;
        private final SimpleStringProperty data;
        private final SimpleStringProperty registoBalcao;
        private final SimpleStringProperty codEquipamento;
        private final SimpleStringProperty codCliente;
        private final SimpleStringProperty tipo;

        public PedidoOrcamentoObs(String codPedidoOrcamento, String data, String registoBalcao, String codEquipamento, String codCliente, String tipo) {
            this.codPedidoOrcamento = new SimpleStringProperty(codPedidoOrcamento);
            this.data = new SimpleStringProperty(data);
            this.registoBalcao = new SimpleStringProperty(registoBalcao);
            this.codEquipamento = new SimpleStringProperty(codEquipamento);
            this.codCliente = new SimpleStringProperty(codCliente);
            this.tipo = new SimpleStringProperty(tipo);
        }

        public String getCodPedidoOrcamento() {
            return codPedidoOrcamento.get();
        }

        public SimpleStringProperty codPedidoOrcamentoProperty() {
            return codPedidoOrcamento;
        }

        public void setCodPedidoOrcamento(String codPedidoOrcamento) {
            this.codPedidoOrcamento.set(codPedidoOrcamento);
        }

        public String getData() {
            return data.get();
        }

        public SimpleStringProperty dataProperty() {
            return data;
        }

        public void setData(String data) {
            this.data.set(data);
        }

        public String getRegistoBalcao() {
            return registoBalcao.get();
        }

        public SimpleStringProperty registoBalcaoProperty() {
            return registoBalcao;
        }

        public void setRegistoBalcao(String registoBalcao) {
            this.registoBalcao.set(registoBalcao);
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

        public String getCodCliente() {
            return codCliente.get();
        }

        public SimpleStringProperty codClienteProperty() {
            return codCliente;
        }

        public void setCodCliente(String codCliente) {
            this.codCliente.set(codCliente);
        }

        public String getTipo() {
            return tipo.get();
        }

        public SimpleStringProperty tipoProperty() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo.set(tipo);
        }
    }
}
