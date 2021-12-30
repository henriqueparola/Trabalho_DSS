package com.controllers.TecnicoDeReparacoes;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaOrcamentos.Orcamento;
import com.business.SubsistemaOrcamentos.OrcamentoFixo;
import com.business.SubsistemaOrcamentos.OrcamentoProgramado;
import com.controllers.Global.Modal;
import com.controllers.Orcamentos.GerirPlanoController;
import com.controllers.Orcamentos.OrcamentoFixoConcluidoController;
import com.controllers.Orcamentos.OrcamentosController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class TecnicoDeReparacoesController implements Initializable {
    @FXML
    TableView table;
    @FXML
    Text total = new Text();
    @FXML Text duracaoMedia = new Text();
    @FXML
    TextField codTecnicoInput = new TextField();
    private ObservableList<OrcamentoObs> orcamentos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    private void getOrcamentosDoTecnico(){
        orcamentos.removeAll(orcamentos);
        for (String codOrcamento: model.getOrcamentosPorPagar()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (!codTecnicoInput.getText().equals("") && o.getCodTecnico().equals(codTecnicoInput.getText())){
                    if (o instanceof OrcamentoProgramado){
                        OrcamentoProgramado op = (OrcamentoProgramado) o;
                        System.out.println("bool:" + op.getCodOrcamento());
                        if (op.orcamentoConcluido()) {
                            orcamentos.add(
                                    new OrcamentoObs(
                                            op.getCodOrcamento(),
                                            op.getCodTecnico(),
                                            op.getCodCliente(),
                                            op.getCodEquipamento(),
                                            String.valueOf(op.getTempoRealTotal()),
                                            String.valueOf(op.getPrecoRealTotal())
                                    )
                            );
                        }
                    }else{
                        OrcamentoFixo op = (OrcamentoFixo) o;
                        orcamentos.add(
                                new OrcamentoObs(
                                        op.getCodOrcamento(),
                                        op.getCodTecnico(),
                                        op.getCodCliente(),
                                        op.getCodEquipamento(),
                                        "1",
                                        String.valueOf(op.getPrecoFixo())
                                )
                        );
                    }
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
        for (String codOrcamento: model.getOrcamentosPagos()){
            try {
                Orcamento o = model.getOrcamento(codOrcamento);
                if (!codTecnicoInput.getText().equals("") && o.getCodTecnico().equals(codTecnicoInput.getText())){
                    if (o instanceof OrcamentoProgramado){
                        OrcamentoProgramado op = (OrcamentoProgramado) o;
                        System.out.println("bool:" + op.getCodOrcamento());
                        if (op.orcamentoConcluido()) {
                            orcamentos.add(
                                    new OrcamentoObs(
                                            op.getCodOrcamento(),
                                            op.getCodTecnico(),
                                            op.getCodCliente(),
                                            op.getCodEquipamento(),
                                            String.valueOf(op.getTempoRealTotal()),
                                            String.valueOf(op.getPrecoRealTotal())
                                    )
                            );
                        }
                    }else{
                        OrcamentoFixo op = (OrcamentoFixo) o;
                        orcamentos.add(
                                new OrcamentoObs(
                                        op.getCodOrcamento(),
                                        op.getCodTecnico(),
                                        op.getCodCliente(),
                                        op.getCodEquipamento(),
                                        "1",
                                        String.valueOf(op.getPrecoFixo())
                                )
                        );
                    }
                }
            } catch (OrcamentoInvalidoException e) {
                System.out.println("Orçamento inválido");
                //e.printStackTrace();
            }
        }
        if (orcamentos.size() > 0){
            duracaoMedia.setText("");
            double i = 0;
            total.setText(String.valueOf(orcamentos.size()));
            for (OrcamentoObs o: orcamentos){
                i += Double.parseDouble(o.getPrazo());
            }
            double media = i / orcamentos.size();
            duracaoMedia.setText(String.valueOf(media));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<OrcamentoObs,String> codOrcamentoColumn = new TableColumn<>("Código Orçamento");
        TableColumn<OrcamentoObs,String> prazoColumn = new TableColumn<>("Prazo real");
        TableColumn<OrcamentoObs,String> precoColumn = new TableColumn<>("Preço real");
        TableColumn<OrcamentoObs,Void> planoDeTrabalhoColumn = new TableColumn<>("Plano de Trabalho");

        planoDeTrabalhoColumn.setCellFactory(param -> new TableCell<OrcamentoObs,Void>() {
            private final Button seeButton = new Button("Ver");
            {
                seeButton.setOnAction(event -> {
                    System.out.println(this.getTableRow().getItem().getCodOrcamento());
                    VerPlanoController c = new VerPlanoController(
                            this.getTableRow().getItem().getCodOrcamento(),
                            ""
                    );

                    try {
                        Orcamento o = model.getOrcamento(this.getTableRow().getItem().getCodOrcamento());
                        if (o instanceof OrcamentoProgramado){
                            showModalWithController("/view/tecnicos/verPlano.fxml","Centro de Reparações",c);
                        }else{
                            Modal.showModal("/view/tecnicos/verOE.fxml","Centro de Reparações");
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
        prazoColumn.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        codOrcamentoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        prazoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        precoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        planoDeTrabalhoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        table.getColumns().clear();
        table.getColumns().addAll(
                codOrcamentoColumn,
                prazoColumn,
                precoColumn,
                planoDeTrabalhoColumn
        );

        getOrcamentosDoTecnico();
        table.setItems(orcamentos);

        codTecnicoInput.setPromptText("Código");
        codTecnicoInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.equals("")){
                    codTecnicoInput.setText(newValue);
                    getOrcamentosDoTecnico();
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

    private void showModalWithController(String fxmlName,String title, VerPlanoController c){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(VerPlanoController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(VerPlanoController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
        }
    }
}
