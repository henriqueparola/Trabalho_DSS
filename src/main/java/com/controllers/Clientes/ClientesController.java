package com.controllers.Clientes;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.ICentroReparacoesLN;
import com.business.SubsistemaClientes.Cliente;
import com.controllers.Global.Modal;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {
    @FXML
    TableView table;
    @FXML
    TextField nifInput;
    private ObservableList<ClienteObs> todosClientes = FXCollections.observableArrayList();
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    public void adicionarClienteForm(ActionEvent actionEvent) {
        Modal.showModal("/view/clientes/adicionarCliente.fxml","Centro de Reparações");
        getTodosClientes();
    }

    private void getTodosClientes(){
        todosClientes.removeAll(todosClientes);
        for (String codCliente: model.getClientes()){
            try {
                Cliente c = model.getCliente(codCliente);
                todosClientes.add(new ClienteObs(
                        c.getNif(),
                        c.getNome(),
                        c.getEmail(),
                        c.getTelemovel()
                ));
            } catch (ClienteInvalidoException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<ClienteObs,String> nifColumn = new TableColumn<>("NIF");
        TableColumn<ClienteObs,String> nomeColumn = new TableColumn<>("Nome");
        TableColumn<ClienteObs,String> eMailColumn = new TableColumn<>("E-mail");
        TableColumn<ClienteObs,String> telemovelColumn = new TableColumn<>("Telemóvel");

        nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telemovelColumn.setCellValueFactory(new PropertyValueFactory<>("telemovel"));

        nifColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        nomeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        eMailColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        telemovelColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        table.getColumns().clear();
        table.getColumns().addAll(
                nifColumn,
                nomeColumn,
                eMailColumn,
                telemovelColumn
        );

        getTodosClientes();
        table.setItems(todosClientes);

        nifInput.setPromptText("Procure por um NIF");
        nifInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == ""){
                    getTodosClientes();
                }else{
                    try {
                        todosClientes.removeAll(todosClientes);
                        Cliente c = model.getCliente(newValue);
                        todosClientes.add(new ClienteObs(c.getNif(),c.getNome(),c.getEmail(),c.getTelemovel()));
                    } catch (ClienteInvalidoException e) {
                       // e.printStackTrace();
                    }
                }
            }
        });
    }

    public class ClienteObs {
        private final SimpleStringProperty nif;
        private final SimpleStringProperty nome;
        private final SimpleStringProperty email;
        private final SimpleStringProperty telemovel;

        public ClienteObs(String nif, String nome, String email, String telemovel) {
            this.nif = new SimpleStringProperty(nif);
            this.nome = new SimpleStringProperty(nome);
            this.email = new SimpleStringProperty(email);
            this.telemovel = new SimpleStringProperty(telemovel);
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

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String getTelemovel() {
            return telemovel.get();
        }

        public SimpleStringProperty telemovelProperty() {
            return telemovel;
        }

        public void setTelemovel(String telemovel) {
            this.telemovel.set(telemovel);
        }
    }
}
