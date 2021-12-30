package com.business.SubsistemaEquipamentos;

import java.io.Serializable;

public class Equipamento implements Serializable {
    private String codEquipamento;
    private String nomeEquipamento;
    private String nifCliente;
    private static int nextId = 0;

    public Equipamento(String nomeEquipamento, String nifCliente) {
        this.nomeEquipamento = nomeEquipamento;
        this.nifCliente = nifCliente;
        this.codEquipamento = getNextId();
    }

    public Equipamento(Equipamento e) {
        this.nomeEquipamento = e.getNomeEquipamento();
        this.nifCliente = e.getNifCliente();
        this.codEquipamento = e.getCodEquipamento();
    }

    public String getCodEquipamento() {
        return codEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    private String getNextId() {
        try {
            return Integer.toString(nextId);
        } finally {
            nextId++;
        }
    }

    public Equipamento clone() {
        return new Equipamento(this);
    }

}
