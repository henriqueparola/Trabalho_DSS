package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.Excecoes.PassoInvalidoException;
import com.business.Excecoes.SemSubPassosException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaOrcamentos.Orcamento;
import com.business.SubsistemaOrcamentos.OrcamentoFixo;
import com.business.SubsistemaOrcamentos.OrcamentoProgramado;
import com.business.SubsistemaOrcamentos.Passo;
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

public class GerirPlanoController implements Initializable {
    private final String codOrcamento;
    @FXML
    Text estadoTrue = new Text();
    @FXML
    Text estadoFalse = new Text();
    @FXML
    Text descricao = new Text();
    @FXML
    Text tempoEstimado = new Text();
    @FXML
    Text custoEstimado = new Text();
    @FXML
    TextField tempoRealInput = new TextField();
    @FXML
    TextField custoRealInput = new TextField();
    @FXML
    TableView table;
    private ObservableList<PassoObs> passos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();
    String passoFmt;

    public GerirPlanoController(String codOrcamento,String passoFmt) {
        this.codOrcamento = codOrcamento;
        this.passoFmt = passoFmt;
    }

    @FXML
    void assinalarPassoAction(ActionEvent e){
        System.out.println(custoRealInput.getText() + "-" + tempoRealInput.getText());
        try {
            model.assinalarPasso(
                    Double.parseDouble(tempoRealInput.getText()),
                    Double.parseDouble(custoRealInput.getText()),
                    passoFmt,
                    codOrcamento
            );

            if (!passoFmt.equals("")) {
                boolean estado = model.getPasso(codOrcamento, passoFmt).isEstadoConclusao();
                if (estado == true) {
                    estadoTrue.setText("concluído");
                    estadoFalse.setText("");
                } else {
                    estadoFalse.setText("por fazer");
                    estadoTrue.setText("");
                }

                descricao.setText(model.getPasso(codOrcamento, passoFmt).getDescricao());
                custoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoCustoPecas()));
                tempoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoDuracao()));
                //getSubNiveis();
            }else{
                Orcamento o = model.getOrcamento(codOrcamento);
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    custoEstimado.setText(String.valueOf((op.getPrecoTotal())));
                    tempoEstimado.setText(String.valueOf((op.getPrazo())));
                }else{
                    OrcamentoFixo of = (OrcamentoFixo) o;
                    custoEstimado.setText(String.valueOf((of.getPrecoFixo())));
                    tempoEstimado.setText("urgente");
                }
            }
        } catch (OrcamentoInvalidoException ex) {
            System.out.println("Orçamento inválido");
            // ex.printStackTrace();
        } catch (PassoInvalidoException ex) {
            ex.printStackTrace();
        }
        getSubNiveis();
    }

    private void getSubNiveis() {
        passos.removeAll(passos);
        try {
            for (Passo p : model.getSubPassos(codOrcamento, passoFmt)) {
                passos.add(new PassoObs(
                        String.valueOf(p.getNoPasso()),
                        String.valueOf(p.getPrevisaoCustoPecas()),
                        String.valueOf(p.getPrevisaoDuracao()),
                        p.getCustoPecas(),
                        p.getDuracao(),
                        p.getDescricao()
                ));
            }
        } catch (OrcamentoInvalidoException e) {
            System.out.println("Orcamento inválido");
            // e.printStackTrace();
        } catch (PassoInvalidoException e) {
            System.out.println("passo invalido");
            // e.printStackTrace();
        } catch (SemSubPassosException e) {
            System.out.println("sem subPassos");
            // e.printStackTrace();
        }
    }

    void refreshData(){
        try {
            if (!passoFmt.equals("")) {
                boolean estado = model.getPasso(codOrcamento, passoFmt).isEstadoConclusao();
                if (estado == true) {
                    estadoTrue.setText("concluído");
                    estadoFalse.setText("");
                } else {
                    estadoFalse.setText("por fazer");
                    estadoTrue.setText("");
                }

                descricao.setText(model.getPasso(codOrcamento, passoFmt).getDescricao());
                custoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoCustoPecas()));
                tempoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoDuracao()));
                getSubNiveis();
            }else{
                Orcamento o = model.getOrcamento(codOrcamento);
                descricao.setText("Orçamento do cliente: " + o.getCodCliente());
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    custoEstimado.setText(String.valueOf((op.getPrecoTotal())));
                    tempoEstimado.setText(String.valueOf((op.getPrazo())));
                }else{
                    OrcamentoFixo of = (OrcamentoFixo) o;
                    custoEstimado.setText(String.valueOf((of.getPrecoFixo())));
                    tempoEstimado.setText("urgente");
                }
            }
        } catch (OrcamentoInvalidoException e) {
            e.printStackTrace();
        } catch (PassoInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (!passoFmt.equals("")) {
                boolean estado = model.getPasso(codOrcamento, passoFmt).isEstadoConclusao();
                if (estado == true) {
                    estadoTrue.setText("concluído");
                    estadoFalse.setText("");
                } else {
                    estadoFalse.setText("por fazer");
                    estadoTrue.setText("");
                }

                descricao.setText(model.getPasso(codOrcamento, passoFmt).getDescricao());
                custoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoCustoPecas()));
                tempoEstimado.setText(String.valueOf(model.getPasso(codOrcamento, passoFmt).getPrevisaoDuracao()));
            }else{
                Orcamento o = model.getOrcamento(codOrcamento);
                descricao.setText("Orçamento do cliente: " + o.getCodCliente());
                if (o instanceof OrcamentoProgramado){
                    OrcamentoProgramado op = (OrcamentoProgramado) o;
                    custoEstimado.setText(String.valueOf((op.getPrecoTotal())));
                    tempoEstimado.setText(String.valueOf((op.getPrazo())));
                }else{
                    OrcamentoFixo of = (OrcamentoFixo) o;
                    custoEstimado.setText(String.valueOf((of.getPrecoFixo())));
                    tempoEstimado.setText("urgente");
                }
            }
        } catch (OrcamentoInvalidoException e) {
            e.printStackTrace();
        } catch (PassoInvalidoException e) {
            e.printStackTrace();
        }

        TableColumn<PassoObs, String> nPassoColumn = new TableColumn<>("Número");
        TableColumn<PassoObs, String> custoEstColumn = new TableColumn<>("Custo Estimado");
        TableColumn<PassoObs, String> tempEstColumn = new TableColumn<>("Tempo estimado");
        TableColumn<PassoObs, String> custoRealColumn = new TableColumn<>("Custo Real");
        TableColumn<PassoObs, String> tempRealColumn = new TableColumn<>("Tempo Real");
        TableColumn<PassoObs, Void> verDetalhesColumn = new TableColumn<>("Ver detalhes");

        verDetalhesColumn.setCellFactory(param -> new TableCell<PassoObs,Void>() {
            private final Button seeButton = new Button("Ver");
            {
                seeButton.setOnAction(event -> {
                    String newPasso;
                    if (passoFmt.equals("")) newPasso = this.getTableRow().getItem().getnPasso();
                    else newPasso = passoFmt + "#" + this.getTableRow().getItem().getnPasso();
                    GerirPlanoController c = new GerirPlanoController(
                            codOrcamento,
                            newPasso
                    );
                    showModalWithController("/view/orcamentos/gerirPlano.fxml","Centro de Reparações",c);
                    refreshData();
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
        nPassoColumn.setCellValueFactory(new PropertyValueFactory<>("nPasso"));
        custoEstColumn.setCellValueFactory(new PropertyValueFactory<>("custoEstimado"));
        tempEstColumn.setCellValueFactory(new PropertyValueFactory<>("tempoEstimado"));
        custoRealColumn.setCellValueFactory(new PropertyValueFactory<>("custoReal"));
        tempRealColumn.setCellValueFactory(new PropertyValueFactory<>("tempoReal"));

        nPassoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        custoEstColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        tempEstColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        custoRealColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        tempRealColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        verDetalhesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        table.getColumns().clear();
        table.getColumns().addAll(
                nPassoColumn,
                custoEstColumn,
                tempEstColumn,
                custoRealColumn,
                tempRealColumn,
                verDetalhesColumn
        );

        getSubNiveis();
        table.setItems(passos);
    }

    public class PassoObs {
        private final SimpleStringProperty nPasso;
        private final SimpleStringProperty custoEstimado;
        private final SimpleStringProperty tempoEstimado;
        private final SimpleStringProperty custoReal;
        private final SimpleStringProperty tempoReal;
        private final SimpleStringProperty descricao;

        private PassoObs(String nPasso, String custoEstimado, String tempoEstimado, double custoReal, double tempoReal, String descricao) {
            this.nPasso = new SimpleStringProperty(nPasso);
            this.custoEstimado = new SimpleStringProperty(custoEstimado);
            this.tempoEstimado = new SimpleStringProperty(tempoEstimado);
            this.descricao = new SimpleStringProperty(descricao);
            if (custoReal == -1) this.custoReal = new SimpleStringProperty("?");
            else {
                this.custoReal = new SimpleStringProperty(String.valueOf(custoReal));
            }
            if (custoReal == -1 ) this.tempoReal = new SimpleStringProperty("?");
            else {
                this.tempoReal = new SimpleStringProperty(String.valueOf(tempoEstimado));
            }
        }

        public String getnPasso() {
            return nPasso.get();
        }

        public SimpleStringProperty nPassoProperty() {
            return nPasso;
        }

        public void setnPasso(String nPasso) {
            this.nPasso.set(nPasso);
        }

        public String getCustoEstimado() {
            return custoEstimado.get();
        }

        public SimpleStringProperty custoEstimadoProperty() {
            return custoEstimado;
        }

        public void setCustoEstimado(String custoEstimado) {
            this.custoEstimado.set(custoEstimado);
        }

        public String getTempoEstimado() {
            return tempoEstimado.get();
        }

        public SimpleStringProperty tempoEstimadoProperty() {
            return tempoEstimado;
        }

        public void setTempoEstimado(String tempoEstimado) {
            this.tempoEstimado.set(tempoEstimado);
        }

        public String getCustoReal() {
            return custoReal.get();
        }

        public SimpleStringProperty custoRealProperty() {
            return custoReal;
        }

        public void setCustoReal(String custoReal) {
            this.custoReal.set(custoReal);
        }

        public String getTempoReal() {
            return tempoReal.get();
        }

        public SimpleStringProperty tempoRealProperty() {
            return tempoReal;
        }

        public void setTempoReal(String tempoReal) {
            this.tempoReal.set(tempoReal);
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

    private void showModalWithController(String fxmlName,String title, GerirPlanoController c){
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(OrcamentosController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(GerirPlanoController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
        }
    }
}
