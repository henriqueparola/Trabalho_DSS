package com.business.SubsistemaEquipamentos;

import com.business.Excecoes.EquipamentoInvalidoException;

import java.io.Serializable;
import java.util.List;

public interface IEquipamentoLN  {
    //List<codEquipamento> getEquipamentosDoCliente(String nif);
    public List<String> getEquipamentosDoCliente(String nif);
    public void arquivarEquipamentoAbandonado(String codEquipamento);
    public void arquivarEquipamentosAbandonados();
    public Equipamento getEquipamento(String codEquipamento) throws EquipamentoInvalidoException;
    //public codEquipamento registarEquipamento(String codEquipamento);
    public String registarEquipamento(String nif, String nomeEquipamento);
    public void registarEquipamentoPago(String codEquipamento) throws EquipamentoInvalidoException;
    public void registarEquipamentoPorPagar(String codEquipamento) throws EquipamentoInvalidoException;
    public void registarEquipamentoRecusado(String codEquipamento) throws EquipamentoInvalidoException;
    public List<String> getEquipamentosAndamento();
    public List<String> getEquipamentosPago();
    public List<String> getEquipamentosPorPagar();
    public List<String> getEquipamentosRecusado();
    public List<String> getEquipamentosAbandonado();
}
