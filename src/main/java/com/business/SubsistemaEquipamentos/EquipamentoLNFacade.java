package com.business.SubsistemaEquipamentos;

import com.business.Excecoes.EquipamentoInvalidoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquipamentoLNFacade implements IEquipamentoLN {

    //Map<codEquipamento, Equipamento>
    private Map<String, Equipamento> andamento = new HashMap<>();
    private Map<String, Equipamento> abandonados = new HashMap<>();
    private Map<String, Equipamento> porPagar = new HashMap<>();
    private Map<String, Equipamento> pagos = new HashMap<>();
    private Map<String, Equipamento> recusados = new HashMap<>();

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

    public Equipamento getEquipamento(String codEquipamento) throws EquipamentoInvalidoException {
        Equipamento res;
        res = this.andamento.get(codEquipamento);
        if (res != null) return res;

        res = this.pagos.get(codEquipamento);
        if (res != null) return res;

        res = this.porPagar.get(codEquipamento);
        if (res != null) return res;

        res = this.recusados.get(codEquipamento);
        if (res != null) return res;

        res = this.abandonados.get(codEquipamento);
        if (res != null) return res;

        throw new EquipamentoInvalidoException();
    }

    //public codEquipamento registarEquipamento(String codEquipamento);
    public String registarEquipamento(String nif, String nomeEquipamento) {
        Equipamento p = new Equipamento(nomeEquipamento, nif);
        this.andamento.put(p.getCodEquipamento(), p);
        return p.getCodEquipamento();
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

    @Override
    public List<String> getEquipamentosAndamento() {
        return this.andamento.keySet().stream().collect(Collectors.toList());
    }

    public List<String> getEquipamentosPago() {
        return this.pagos.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getEquipamentosPorPagar() {
        return this.porPagar.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getEquipamentosRecusado() {
        return this.recusados.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getEquipamentosAbandonado() {
        return this.abandonados.keySet().stream().collect(Collectors.toList());
    }
}
