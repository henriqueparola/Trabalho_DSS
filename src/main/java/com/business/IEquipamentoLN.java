package main.java.com.business;

import java.util.List;

public interface IEquipamentoLN {
    //List<codEquipamento> getEquipamentosDoCliente(String nif);
    public List<String> getEquipamentosDoCliente(String nif);
    public void arquivarEquipamento(String codEquipamento);
    public void arquivarEquipamentosAbandonados();
    public Equipamento getEquipamento(String codEquipamento);
}
