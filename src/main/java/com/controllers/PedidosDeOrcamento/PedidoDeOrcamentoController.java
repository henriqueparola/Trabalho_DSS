package com.controllers.PedidosDeOrcamento;

import com.business.CentroReparacoesLNFacade;
import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.FuncionarioInvalidoException;
import com.business.Excecoes.ProdutoInvalidoException;
import com.business.Excecoes.TecnicosIndisponiveisException;
import com.business.ICentroReparacoesLN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidoDeOrcamentoController {
    @FXML TextField nifInput;
    @FXML TextField equipamentoInput;
    @FXML TextField registoBalcaoInput;
    ICentroReparacoesLN model = CentroReparacoesLNFacade.getInstance();

    public void addPedidoDeOrcamentoProgramado(ActionEvent event){
        //System.out.println(nifInput.getText());
        //System.out.println(equipamentoInput.getText());
        //System.out.println(registoBalcaoInput.getText());
        if (nifInput.getText() != "" && equipamentoInput.getText() != "" && registoBalcaoInput.getText() != "") {
            try {
                model.registarPedidoOrcamento(nifInput.getText(), equipamentoInput.getText(), registoBalcaoInput.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (ClienteInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Cliente inexistente");
                a.show();
                //System.out.println("Cliente inválido");
            } catch (FuncionarioInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Funcionário inexistente");
                a.show();
                e.printStackTrace();
            }
        }
    }

    public void addPedidoDeOrcamentoExpresso(ActionEvent event){
        if (nifInput.getText() != "" && equipamentoInput.getText() != "" && registoBalcaoInput.getText() != "") {
            try {
                model.registarPedidoOrcamentoExpresso(nifInput.getText(),equipamentoInput.getText(),registoBalcaoInput.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (ClienteInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Cliente inexistente");
                a.show();
                //System.out.println("Cliente inválido");
            } catch (FuncionarioInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Funcionário inexistente");
                a.show();
                e.printStackTrace();
            } catch (ProdutoInvalidoException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Produto não encontrado");
                a.show();
                e.printStackTrace();
            } catch (TecnicosIndisponiveisException e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Técnicos indisponíveis");
                a.show();
                e.printStackTrace();
            }
        }

    }
}
