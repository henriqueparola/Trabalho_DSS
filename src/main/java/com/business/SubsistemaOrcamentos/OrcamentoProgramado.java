package com.business.SubsistemaOrcamentos;

import java.time.LocalDateTime;

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

    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCusto, String passo) {
        this.plano.adicionarPasso(descricao, previsaoTempo, previsaoCusto, passo);
        // Possivelmente atualizarDados
    }

}
