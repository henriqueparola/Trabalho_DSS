package com.controllers.Orcamentos;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.FuncionarioInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;
import com.business.ICentroReparacoesLN;
import com.controllers.PedidosDeOrcamento.PedidosDeOrcamentoController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class OrcamentoController implements Initializable{
    @FXML
    private BorderPane mainPane;
    OrcamentoCController oCController = new OrcamentoCController();
    Node informacoesNode;
    Node planoDeTrabalhoNode;
    @FXML
    Button planoDeTrabalhoButton;
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    @FXML
    private void informacoesAction(ActionEvent event) throws IOException {
        if (oCController.nifInput.getText() == "" ||
                oCController.codFuncionarioInput.getText() == "" ||
                oCController.codPedidoDeOrcamentoInput.getText() == "") {
            mainPane.setCenter(informacoesNode);
        }
    }

    @FXML
    private void planoDeTrabalhoAction(ActionEvent event) throws IOException {
        if (oCController.nifInput.getText() != "" &&
                oCController.codFuncionarioInput.getText() != "" &&
                oCController.codPedidoDeOrcamentoInput.getText() != "") {
            try {
                oCController.codOrcamento = model.registarOrcamentoProgramado(
                        oCController.nifInput.getText(),
                        oCController.codFuncionarioInput.getText(),
                        oCController.codPedidoDeOrcamentoInput.getText()
                );
                System.out.println("cod: " + oCController.codOrcamento);
                mainPane.setCenter(planoDeTrabalhoNode);
            } catch (ClienteInvalidoException e) {
                System.out.println("cliente inválido");
                //e.printStackTrace();
            } catch (FuncionarioInvalidoException e) {
                System.out.println("Funcionário inválido");
                //e.printStackTrace();
            } catch (PedidoOrcamentoInvalidoException e) {
                System.out.println("Pedido de orçamento inválido");
                //e.printStackTrace();
            }
        }
    }

    @FXML
    private void finalizarAction(ActionEvent event) throws IOException {
        try {
            model.enviarEmailConfirmacao(oCController.codOrcamento);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (OrcamentoInvalidoException e) {
            System.out.println("Orçamento inválido");
            //e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader informacoesLoader = new FXMLLoader(OrcamentoController.class.getResource("/view/orcamentos/informacoes.fxml"));
        FXMLLoader planoDeTrabalhoLoader = new FXMLLoader(OrcamentoController.class.getResource("/view/orcamentos/planoDeTrabalho.fxml"));

        informacoesLoader.setController(oCController);
        planoDeTrabalhoLoader.setController(oCController);

        try {
            planoDeTrabalhoNode = planoDeTrabalhoLoader.load();
            informacoesNode = informacoesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            informacoesAction(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
