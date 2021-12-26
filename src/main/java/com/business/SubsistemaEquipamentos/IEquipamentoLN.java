package main.java.com.business.SubsistemaEquipamentos;

import java.util.List;

public interface IEquipamentoLN {
    //List<codEquipamento> getEquipamentosDoCliente(String nif);
    public List<String> getEquipamentosDoCliente(String nif);
    public void arquivarEquipamento(String codEquipamento);
    public void arquivarEquipamentosAbandonados();
    public Equipamento getEquipamento(String codEquipamento);
    //public codEquipamento registarEquipamento(String codEquipamento);
    public String registarEquipamento(String codEquipamento);
    public void registarEquipamentoPago(String codEquipamento);
    public void registarEquipamentoPorPagar(String codEquipamento);
    public void registarEquipamentoRecusado(String codEquipamento);
}
