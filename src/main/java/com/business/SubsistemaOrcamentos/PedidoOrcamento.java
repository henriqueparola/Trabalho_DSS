package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.PedidoOrcamentoInvalidoException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoOrcamento implements Serializable {
    private String codPedidoOrcamento;
    private LocalDateTime data;
    private String registoBalcao;
    private String codEquipamento;
    private String codCliente;
    private static int nextId;

    public PedidoOrcamento(PedidoOrcamento po) {
        this.codPedidoOrcamento = po.getCodPedidoOrcamento();
        this.data = po.getData();
        this.registoBalcao = po.getRegistoBalcao();
        this.codEquipamento = po.getCodEquipamento();
        this.codCliente = po.getCodCliente();
    }
    public PedidoOrcamento(String codEquipamento, LocalDateTime data, String registoBalcao, String codCliente) {
        this.data = data;
        this.registoBalcao = registoBalcao;
        this.codEquipamento = codEquipamento;
        this.codPedidoOrcamento = getNextId();
        this.codCliente = codCliente;
    }

    private static String getNextId() {
        try {
            return Integer.toString(nextId);
        } finally {
            nextId++;
        }
    }

    public String getCodCliente() {
        return codCliente;
    }

    public String getCodPedidoOrcamento() {
        return codPedidoOrcamento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getRegistoBalcao() {
        return registoBalcao;
    }

    public String getCodEquipamento() {
        return codEquipamento;
    }


    public PedidoOrcamento clone() {
        return new PedidoOrcamento(this);
    }
}

