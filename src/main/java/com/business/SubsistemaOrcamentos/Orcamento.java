package com.business.SubsistemaOrcamentos;

public abstract class Orcamento {
    private String codTecnico;
    private String codCliente;
    private String codEquipamento;
    private String codOrcamento;

    private static int nextId = 0;

    public void setCodTecnico(String codTecnico) {
        this.codTecnico = codTecnico;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
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
