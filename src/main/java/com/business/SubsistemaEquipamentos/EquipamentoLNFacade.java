package com.business.SubsistemaEquipamentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipamentoLNFacade implements IEquipamentoLN {

    //Map<codEquipamento, Equipamento>
    private Map<String, Equipamento> andamento;
    private Map<String, Equipamento> abandonados;
    private Map<String, Equipamento> porPagar;
    private Map<String, Equipamento> pagos;
    private Map<String, Equipamento> recusados;


    //List<codEquipamento> getEquipamentosDoCliente(String nif);
    // Provavelmente faz sentido levantar uma exceção
    public List<String> getEquipamentosDoCliente(String nif){
        List<String> equipamentos = new ArrayList<>();
        return null;
    }
    public void arquivarEquipamento(String codEquipamento) {
        //TODO arquivarEquipamento
    }
    public void arquivarEquipamentosAbandonados() {
        //TODO arquivarEquipamentosAbandonados
    }

    public Equipamento getEquipamento(String codEquipamento) {
        //TODO getEquipamento
        return null;
    }

    //public codEquipamento registarEquipamento(String codEquipamento);
    public String registarEquipamento(String codEquipamento) {
        //TODO registarEquipamento
        return null;
    }

    public void registarEquipamentoPago(String codEquipamento) {
        //TODO registarEquipamentoPago
    }

    public void registarEquipamentoPorPagar(String codEquipamento) {
        //TODO  registarEquipamentoPorPagar
    }
    public void registarEquipamentoRecusado(String codEquipamento) {
        //TODO  registarEquipamentoRecusado
    }

}
