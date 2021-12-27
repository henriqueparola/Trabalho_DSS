package com.business.SubsistemaOrcamentos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrcamentoProgramado extends Orcamento {
    private LocalDateTime prazo;
    private double precoTotal;
    private PlanoTrabalho plano;

    public OrcamentoProgramado(String codTecnico, String codCliente, String codEquipamento) {
        super.setCodTecnico(codTecnico);
        super.setCodCliente(codCliente);
        super.setCodEquipamento(codEquipamento);
        super.setCodOrcamento(Orcamento.getNextId());
        this.precoTotal = 0;
        this.plano = new PlanoTrabalho();
    }
}
