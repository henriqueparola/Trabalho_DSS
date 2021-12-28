package com.business.SubsistemaOrcamentos;

import java.time.LocalDateTime;

public class PedidoOrcamento {
    private String codPedidoOrcamento;
    private LocalDateTime data;
    private String registoBalcao;
    private String codEquipamento;
    private String codCliente;
    private static int nextId = 0;

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

}

