package main.java.com.business;

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
    public List<String> getEquipamentosDoCliente(String nif) {
        //TODO getEquipamentosDoCliente
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
}
