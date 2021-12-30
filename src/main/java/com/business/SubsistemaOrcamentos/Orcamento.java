package com.business.SubsistemaOrcamentos;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Orcamento implements Serializable {
    private String codTecnico;
    private String codCliente;
    private String codEquipamento;
    private String codOrcamento;
    private LocalDateTime dataCriacao;

    private static int nextId;

    public void setCodTecnico(String codTecnico) {
        this.codTecnico = codTecnico;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public void setDataCriacao(LocalDateTime data){
        this.dataCriacao = data;
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public String getCodTecnico() {
        return codTecnico;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public String getCodEquipamento() {
        return codEquipamento;
    }

    public static String getNextId() {
        String r = Integer.toString(nextId);
        nextId++;
        return r;
    }


    public String getCodOrcamento() {
        return codOrcamento;
    }

    public void setCodOrcamento(String codOrcamento) {
        this.codOrcamento = codOrcamento;
    }

    public abstract Orcamento clone();

}
