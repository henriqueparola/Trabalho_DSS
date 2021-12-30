package com.business.SubsistemaOrcamentos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoOrcamentoFixo extends PedidoOrcamento implements Serializable {
    private String responsavel;

    public String getResponsavel() {
        return this.responsavel;
    }

    public PedidoOrcamentoFixo(String codEquipamento, LocalDateTime data, String registoBalcao, String codCliente, String responsavel) {
        super(codEquipamento, data, registoBalcao, codCliente);
        this.responsavel = responsavel;
    }

    public PedidoOrcamentoFixo(PedidoOrcamentoFixo pedido) {
        super(pedido);
        this.responsavel = pedido.responsavel;
    }

    public PedidoOrcamentoFixo clone(){
        return new PedidoOrcamentoFixo(this);
    }
}
