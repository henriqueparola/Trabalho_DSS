package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.OrcamentoInvalidoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrcamentosLNFacade implements IOrcamentosLN {

    //Map<codOrcamento, Orcamento>
    private Map<String, Orcamento> porConfirmar;
    private Map<String, Orcamento> andamento;
    private Map<String, Orcamento> porPagar;
    private Map<String, Orcamento> pagos;
    private Map<String, Orcamento> arquivados;

    //Map<codPedidoOrcamento, PedidoOrcamento>
    private Map<String, PedidoOrcamento> pedidos;
    int nextId = 0;


    public List<String> getOrcamentosDoTecnico(String codTecnico) {
        //TODO getOrcamentosDoTecnico
        return null;
    }
    public List<String> getOrcamentos() {
        //TODO getOrcamentos
        return null;
    }
    public Orcamento getOrcamento(String codOrcamento) {
        //TODO getOrcamento
        return null;
    }
    public void registarPagamento(String codOrcamento) {
        //TODO registarPagamento
    }
    public void arquivarOrcamentoRecusado(String codOrcamento) {
        //TODO arquivarOrcamentoRecusado
    }
    public void arquivarOrcamentosSemConfirmacao() {
        //TODO arquivarOrcamentosSemConfirmacao
    }
    // public codOrcamento registarOrcamento(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codEquipamento) {
        Orcamento o = new OrcamentoProgramado(codTecnico, nif, codEquipamento);
        this.porConfirmar.put(o.getCodOrcamento(), o);
        return o.getCodOrcamento();
    }
    public void registarOrcamentoConcluido(String codOrcamento) {
        //TODO  registarOrcamentoConcluido
    }
    // Não me lembro do contexto desta função
    // Mas ela não deveria retornar alguma coisa?
    public void verificarCustoUltrapassado(String codOrcamennto) {
        //TODO  verificarCustoUltrapassado
    }
    // public codOrcamento registarOrcamentoFixo(...);
    public String registarOrcamentoFixo(String nif, String produto, String codPedidoOrcamento) {
        //TODO  registarOrcamentoFixo
        return null;
    }
    public void registarOrcamentoFixoConcluido(String codOrcamento) {
        //TODO  registarOrcamentoFixoConcluido
    }
    // public codPlano registarPlanoTrabalho();
    // TODO Verificar este método
    public String registarPlanoTrabalho() {
        //TODO registarPlanoTrabalho
        return null;
    }
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCusto, String codOrcamento, String passo) throws OrcamentoInvalidoException {
        Orcamento orcamento = this.porConfirmar.get(codOrcamento);
        if (orcamento instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) orcamento;
            //orcamentoP.adicionarPasso(descricao,previsaoTempo, custoPecas, passo);
        } else throw new OrcamentoInvalidoException();
    }
    // Tinhamos concordado que os noPasso era uma String certo?
    public void assinalarPasso(LocalDateTime duracao, double custo, String passo, String codOrcamento) {
        //TODO assinalarPasso
    }
    public PlanoTrabalho getPlanoTrabalho(String codOrcamento) {
        //TODO getPlanoTrabalho
        return null;
    }

    // Métodos relativos aos pedidos de orçamento
    public void registarPedidoOrcamento(String nif, String nomeEquipamento, String codFunc) {
        //TODO registarPedidoOrcamento
    }
    // public List<codPedidoOrcamento> getPedidosOrcamento();
    public List<String> getPedidosOrcamento() {
        //TODO  getPedidosOrcamento
        return null;
    }
    // public codEquipamento getEquipamentoPedido(String codPedidoOrcamento);
    public String getEquipamentoPedido(String codPedidoOrcamento) {
        //TODO getEquipamentnosPedido
        return null;
    }
    public PedidoOrcamento getPedidoOrcamento(String codPedidoOrcamento) {
        //TODO getPedidoOrcamento
        return null;
    }
    public void registarPedidoOrcamentoExpresso(String nif, String nomeEquipamento, String codFunc, String codTecnico) {
        //TODO registarPedidoOrcamentoExpresso
    }

    //public codEquipamento getCodEquipamentoOrcamento(String codOrcamento);
    public String getCodEquipamentoOrcamento(String codOrcamento) {
        //TODO getCodEquipamentoOrcamento
        return null;
    }
    public void registarOrcamentoAndamento(String codOrcamento) {
        //TODO registarOrcamentoAndamento
    }
    public void removePedidoOrcamento(String codPedidoOrcamento) {
        //TODO removePedidoOrcamento
    }

    @Override
    public boolean validarPedidoOrcamento(String codPedidoOrcamento) {
        PedidoOrcamento po = this.pedidos.get(codPedidoOrcamento);
        return (po != null);
    }
}
