package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.EquipamentoInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.Excecoes.PassoInvalidoException;
import com.business.Excecoes.SemSubPassosException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaEquipamentos.Equipamento;
import com.business.SubsistemaOrcamentos.Passo;
import com.controllers.Equipamentos.EquipamentosController;
import com.controllers.Global.Modal;
import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class OrcamentoCController implements Initializable {
    @FXML
    public TextField nifInput = new TextField();
    @FXML
    public TextField codFuncionarioInput = new TextField();
    @FXML
    public TextField codPedidoDeOrcamentoInput = new TextField();
    @FXML
    public TextField nPassoInput = new TextField();
    @FXML
    public TextField descricaoInput = new TextField();
    @FXML
    public TextField tempoEstimadoInput = new TextField();
    @FXML
    public TextField custoEstimadoInput = new TextField();
    @FXML
    TableView table;
    public String codOrcamento;
    private ObservableList<PassoObs> passos = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();
    String passoFmt = "";

    public void inserirPassoAction(ActionEvent e) {
        System.out.println(custoEstimadoInput.getText() + "-" + tempoEstimadoInput.getText() + "-" + descricaoInput.getText() + "-" + nPassoInput.getText());
        LocalDateTime time = LocalDateTime.of(0,1,1,0,0,0,0);
        time.plusHours(Long.parseLong(tempoEstimadoInput.getText()));
        try {
            model.adicionarPasso(
                    descricaoInput.getText(),
                    time,
                    Double.parseDouble(custoEstimadoInput.getText()),
                    codOrcamento,
                    nPassoInput.getText()
                    );
        } catch (OrcamentoInvalidoException ex) {
            System.out.println("Orçamento inválido");
            // ex.printStackTrace();
        }
        getSubNiveis();
    }

    private void showModalWithController(String fxmlName, String title, VerPassoController c) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(OrcamentoCController.class.getResource(fxmlName));
            loader.setController(c);
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(String.valueOf(OrcamentoCController.class.getResource("/css/style.css")));
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSubNiveis() {
        passos.removeAll(passos);
        try {
            for (Passo p : model.getSubPassos(codOrcamento, "")) {
                // abaixo nao interessa
                passos.add(new PassoObs(
                        String.valueOf(p.getNoPasso()),
                        String.valueOf(p.getPrevisaoCustoPecas()),
                        p.getPrevisaoDuracao().toString(),
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<PassoObs, String> nPassoColumn = new TableColumn<>("Número");
        TableColumn<PassoObs, String> custoColumn = new TableColumn<>("Custo");
        TableColumn<PassoObs, String> previsaoDoTempoColumn = new TableColumn<>("Previsão do tempo");
        TableColumn<PassoObs, Void> verDetalhesColumn = new TableColumn<>("Ver detalhes");

        verDetalhesColumn.setCellFactory(param -> new TableCell<PassoObs, Void>() {
            private final Button seeButton = new Button("Ver detalhes");

            {
                seeButton.setOnAction(event -> {
                    System.out.println("Passo: " + this.getTableRow().getItem().getnPasso());
                    VerPassoController c = new VerPassoController(
                            this.getTableRow().getItem().getnPasso(),
                            codOrcamento
                    );
                   // System.out.println("Cheguei");
                    showModalWithController("/view/orcamentos/verPasso.fxml", "Centro de Reparações", c);
                   // System.out.println("Cheguei");
                });
            }

            @Override
            protected void updateItem(Void passo, boolean empty) {
                super.updateItem(passo, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(seeButton);
                }
            }
        });
        custoColumn.setCellValueFactory(new PropertyValueFactory<>("custoEstimado"));
        previsaoDoTempoColumn.setCellValueFactory(new PropertyValueFactory<>("tempoEstimado"));
        nPassoColumn.setCellValueFactory(new PropertyValueFactory<>("nPasso"));

        nPassoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        custoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        previsaoDoTempoColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        verDetalhesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        table.getColumns().clear();
        table.getColumns().addAll(
                nPassoColumn,
                custoColumn,
                previsaoDoTempoColumn,
                verDetalhesColumn
        );

        getSubNiveis();
        table.setItems(passos);
    }

    public class PassoObs {
        private final SimpleStringProperty nPasso;
        private final SimpleStringProperty custoEstimado;
        private final SimpleStringProperty tempoEstimado;
        private final SimpleStringProperty descricao;

        private PassoObs(String nPasso, String custoEstimado, String tempoEstimado, String descricao) {
            this.nPasso = new SimpleStringProperty(nPasso);
            this.custoEstimado = new SimpleStringProperty(custoEstimado);
            this.tempoEstimado = new SimpleStringProperty(tempoEstimado);
            this.descricao = new SimpleStringProperty(descricao);
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
}
