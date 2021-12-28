package com.business.SubsistemaOrcamentos;

public class OrcamentoFixo extends Orcamento {
    private double precoFixo;
    private String produto;


    public OrcamentoFixo(OrcamentoFixo o) {
        super.setCodTecnico(o.getCodTecnico());
        super.setCodCliente(o.getCodCliente());
        super.setCodEquipamento(o.getCodEquipamento());
        super.setCodOrcamento(o.getCodOrcamento());
        this.precoFixo = o.getPrecoFixo();
        this.produto = o.getProduto();
    }
    public OrcamentoFixo(String codTecnico, String codCliente, String codEquipamento, double precoFixo, String produto) {
        super.setCodTecnico(codTecnico);
        super.setCodCliente(codCliente);
        super.setCodEquipamento(codEquipamento);
        super.setCodOrcamento(Orcamento.getNextId());
        this.precoFixo = precoFixo;
        this.produto = produto;
    }

    public double getPrecoFixo() {
        return precoFixo;
    }

    public String getProduto() {
        return produto;
    }

    public Orcamento clone() {
        return new OrcamentoFixo(this);
    }
}
