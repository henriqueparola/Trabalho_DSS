package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.PassoInvalidoException;
import com.business.Excecoes.SemSubPassosException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class OrcamentoProgramado extends Orcamento implements Serializable {
    private double prazo;
    private double precoTotal;
    private PlanoTrabalho plano;
    private double precoRealTotal;
    private double tempoRealTotal;

    public OrcamentoProgramado(OrcamentoProgramado o) {
        super.setCodTecnico(o.getCodTecnico());
        super.setCodCliente(o.getCodCliente());
        super.setCodEquipamento(o.getCodEquipamento());
        super.setCodOrcamento(o.getCodOrcamento());
        this.plano = o.getPlano();
        this.prazo = o.getPrazo();
        this.precoTotal = o.getPrecoTotal();
        this.precoRealTotal = o.getPrecoRealTotal();
        this.tempoRealTotal = o.getTempoRealTotal();
    }
    public OrcamentoProgramado(String codTecnico, String codCliente, String codEquipamento) {
        super.setCodTecnico(codTecnico);
        super.setCodCliente(codCliente);
        super.setCodEquipamento(codEquipamento);
        super.setCodOrcamento(Orcamento.getNextId());
        this.precoTotal = 0;
        this.plano = new PlanoTrabalho();
        super.setDataCriacao(LocalDateTime.now());
        this.precoRealTotal = 0;
    }

    public void adicionarPasso(String descricao, double previsaoTempo, double previsaoCusto, String passo) {
        this.plano.adicionarPasso(descricao, previsaoTempo, previsaoCusto, passo);
        this.prazo += previsaoTempo;
        this.precoTotal += previsaoCusto;
        // Possivelmente atualizarDados
    }

    public void assinalarPasso(double duracao, double custo, String passo) throws PassoInvalidoException {
        this.plano.assinalarPasso(duracao,custo,passo);
        this.precoRealTotal += custo;
        this.tempoRealTotal += duracao;
    }

    public Passo getPasso(String passo) throws PassoInvalidoException {
        return this.plano.getPasso(passo);
    }

    public List<Passo> getSubPassos(String passo) throws PassoInvalidoException, SemSubPassosException {
        return this.plano.getSubPassos(passo);
    }

    public PlanoTrabalho getPlano() {
        return this.plano.clone();
    }

    public Orcamento clone() {
        return new OrcamentoProgramado(this);
    }

    public double getPrazo() {
        return prazo;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public double getPrecoRealTotal() {
        return precoRealTotal;
    }

    public double getTempoRealTotal() {
        return tempoRealTotal;
    }

    public boolean orcamentoConcluido() {
        return this.getPlano().concluido();
    }
}
