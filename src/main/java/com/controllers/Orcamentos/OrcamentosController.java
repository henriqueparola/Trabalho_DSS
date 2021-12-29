package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaEquipamentos.Equipamento;
import com.business.SubsistemaOrcamentos.Orcamento;
import com.business.SubsistemaOrcamentos.OrcamentoFixo;
import com.business.SubsistemaOrcamentos.OrcamentoProgramado;
import com.controllers.Clientes.ClientesController;
import com.controllers.Equipamentos.EquipamentosController;
import com.controllers.Global.Modal;
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

public class OrcamentosController implements Initializable {
    @FXML
    TableView table;
    @FXML
    TextField codOrcamentoInput;
    private ObservableList<OrcamentoObs> orcamentos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();
    String stateSelected = "";

    public void adicionarOrcamentoProgramado(ActionEvent actionEvent) {
        Modal.showModal("/view/orcamentos/adicionarOrcamentoProgramado.fxml","Centro de Reparações");
        getOrcamentosPorConfirmar();
    }

    public void adicionarOrcamentoFixo(ActionEvent actionEvent) {
        Modal.showModal("/view/orcamentos/adicionarOrcamentoProgramado.fxml","Centro de Reparações");
    }

    public void arquivarOrcamentoRecusado(ActionEvent actionEvent) {
        Modal.showModal("/view/orcamentos/arquivarOrcamentoRecusado.fxml","Centro de Reparações");
        getOrcamentosPorConfirmar();
    }

    public void registarOrcamentoConfirmado(ActionEvent actionEvent) {
        Modal.showModal("/view/orcamentos/registarOrcamentoConfirmado.fxml","Centro de Reparações");
        getOrcamentosPorConfirmar();
    }

    @FXML
    void porPagarAction(ActionEvent e){
        getOrcamentosPorPagar();
        stateSelected = "porPagar";
    }

    @FXML
    void pagoAction(ActionEvent e){
        getOrcamentosPagos();
        stateSelected = "pago";
    }

    @FXML
    void recusadoAction(ActionEvent e){
        getOrcamentosRecusados();
        stateSelected = "recusado";
    }

    @FXML
    void emAndamentoAction(ActionEvent e){
        getOrcamentosAndamento();
        stateSelected = "andamento";
    }

    @FXML
    void porConfirmarAction(ActionEvent e){
        getOrcamentosPorConfirmar();
        stateSelected = "porConfirmar";
    }

    private void getOrcamentosAndamento(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosAndamento()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    orcamentos.add(
                        new OrcamentoObs(
                                op.getCodOrcamento(),
                                op.getCodTecnico(),
                                op.getCodCliente(),
                                op.getCodEquipamento(),
                                String.valueOf(op.getPrecoTotal()),
                                String.valueOf(op.getPrecoTotal())
                        )
                    );
                }else{
                    OrcamentoFixo op = (OrcamentoFixo) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    "",
                                    String.valueOf(op.getPrecoFixo())
                            )
                    );
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getOrcamentosPorPagar(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosPorPagar()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    String.valueOf(op.getPrazo()),
                                    String.valueOf(op.getPrecoTotal())
                            )
                    );
                }else{
                    OrcamentoFixo op = (OrcamentoFixo) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    "",
                                    String.valueOf(op.getPrecoFixo())
                            )
                    );
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getOrcamentosPagos(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosPagos()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    String.valueOf(op.getPrazo()),
                                    String.valueOf(op.getPrecoTotal())
                            )
                    );
                }else{
                    OrcamentoFixo op = (OrcamentoFixo) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    "",
                                    String.valueOf(op.getPrecoFixo())
                            )
                    );
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getOrcamentosPorConfirmar(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosPorConfirmar()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    String.valueOf(op.getPrazo()),
                                    String.valueOf(op.getPrecoTotal())
                            )
                    );
                }else{
                    OrcamentoFixo op = (OrcamentoFixo) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    "",
                                    String.valueOf(op.getPrecoFixo())
                            )
                    );
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    private void getOrcamentosRecusados(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosRecusados()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    String.valueOf(op.getPrazo()),
                                    String.valueOf(op.getPrecoTotal())
                            )
                    );
                }else{
                    OrcamentoFixo op = (OrcamentoFixo) o;
                    orcamentos.add(
                            new OrcamentoObs(
                                    op.getCodOrcamento(),
                                    op.getCodTecnico(),
                                    op.getCodCliente(),
                                    op.getCodEquipamento(),
                                    "",
                                    String.valueOf(op.getPrecoFixo())
                            )
                    );
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<OrcamentoObs,String> codOrcamentoColumn = new TableColumn<>("Código Orçamento");
        TableColumn<OrcamentoObs,String> codTecnicoColumn = new TableColumn<>("Código Ténico");
        TableColumn<OrcamentoObs,String> codClienteColumn = new TableColumn<>("Código Cliente");
        TableColumn<OrcamentoObs,String> codEquipamentoColumn = new TableColumn<>("Código Equipamento");
        TableColumn<OrcamentoObs,String> prazoColumn = new TableColumn<>("Prazo");
        TableColumn<OrcamentoObs,String> precoColumn = new TableColumn<>("Preço");
        TableColumn<OrcamentoObs,Void> planoDeTrabalhoColumn = new TableColumn<>("Plano de Trabalho");

        planoDeTrabalhoColumn.setCellFactory(param -> new TableCell<OrcamentoObs,Void>() {
            private final Button seeButton = new Button("Gerir");
            {
                seeButton.setOnAction(event -> {
                    System.out.println(this.getTableRow().getItem().getCodOrcamento());
                    GerirPlanoController c = new GerirPlanoController(
                            this.getTableRow().getItem().getCodOrcamento(),
                            ""
                    );

                    try {
                        Orcamento o = model.getOrcamento(this.getTableRow().getItem().getCodOrcamento());
                        if (o instanceof OrcamentoProgramado){
                            showModalWithController("/view/orcamentos/gerirPlano.fxml","Centro de Reparações",c);
                        }else{
                            OrcamentoFixoConcluidoController c2 = new OrcamentoFixoConcluidoController(this.getTableRow().getItem().getCodOrcamento());
                            showModalWithController2("/view/orcamentos/orcamentoFixoConcluido","Centro de Reparações",c2);
                        }
                    } catch (OrcamentoInvalidoException e) {
                        e.printStackTrace();
                    }
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

        codOrcamentoColumn.setCellValueFactory(new PropertyValueFactory<>("codOrcamento"));
        codTecnicoColumn.setCellValueFactory(new PropertyValueFactory<>("codTecnico"));
        codClienteColumn.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
        codEquipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("codEquipamento"));
        prazoColumn.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        codOrcamentoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        codTecnicoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        codClienteColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        codEquipamentoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        prazoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        precoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        planoDeTrabalhoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.10));

        table.getColumns().clear();
        table.getColumns().addAll(
                codOrcamentoColumn,
                codTecnicoColumn,
                codClienteColumn,
                codEquipamentoColumn,
                prazoColumn,
                precoColumn,
                planoDeTrabalhoColumn
        );

        getOrcamentosPorConfirmar();
        table.setItems(orcamentos);

        codOrcamentoInput.setPromptText("Procure por um orçamento");
        codOrcamentoInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == ""){
                    switch (stateSelected){
                        case "andamento":
                            getOrcamentosAndamento();
                            break;
                        case "recusado":
                            getOrcamentosRecusados();
                            break;
                        case "porPagar":
                            getOrcamentosPorPagar();
                            break;
                        case "pago":
                            getOrcamentosPagos();
                            break;
                        case "porConfirmar":
                            getOrcamentosPorConfirmar();
                            break;
                    }
                }else{
                    try {
                        orcamentos.removeAll(orcamentos);
                        Orcamento o = model.getOrcamento(newValue);
                        if (o instanceof OrcamentoProgramado){
                            OrcamentoProgramado op = (OrcamentoProgramado) o;
                            orcamentos.add(
                                    new OrcamentoObs(
                                            op.getCodOrcamento(),
                                            op.getCodTecnico(),
                                            op.getCodCliente(),
                                            op.getCodEquipamento(),
                                            String.valueOf(op.getPrazo()),
                                            String.valueOf(op.getPrecoTotal())
                                    )
                            );
                        }else{
                            OrcamentoFixo op = (OrcamentoFixo) o;
                            orcamentos.add(
                                    new OrcamentoObs(
                                            op.getCodOrcamento(),
                                            op.getCodTecnico(),
                                            op.getCodCliente(),
                                            op.getCodEquipamento(),
                                            "",
                                            String.valueOf(op.getPrecoFixo())
                                    )
                            );
                        }
                    }catch (OrcamentoInvalidoException e) {
                        // e.printStackTrace();
                    }
                }
            }
        });
    }

    public class OrcamentoObs {
        private final SimpleStringProperty codOrcamento;
        private final SimpleStringProperty codTecnico;
        private final SimpleStringProperty codCliente;
        private final SimpleStringProperty codEquipamento;
        private final SimpleStringProperty prazo;
        private final SimpleStringProperty preco;


        public OrcamentoObs(String codOrcamento, String codTecnico, String codCliente, String codEquipamento, String prazo, String preco) {
            this.codTecnico = new SimpleStringProperty(codTecnico);
            this.codCliente = new SimpleStringProperty(codCliente);
            this.codEquipamento = new SimpleStringProperty(codEquipamento);
            this.prazo = new SimpleStringProperty(prazo);
            this.preco = new SimpleStringProperty(preco);
            this.codOrcamento = new SimpleStringProperty(codOrcamento);
        }

        public String getCodOrcamento() {
            return codOrcamento.get();
        }

        public SimpleStringProperty codOrcamentoProperty() {
            return codOrcamento;
        }

        public void setCodOrcamento(String codOrcamento) {
            this.codOrcamento.set(codOrcamento);
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

    private void showModalWithController(String fxmlName,String title, GerirPlanoController c){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(OrcamentosController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(OrcamentosController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showModalWithController2(String fxmlName,String title, OrcamentoFixoConcluidoController c){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(OrcamentosController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(OrcamentosController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
