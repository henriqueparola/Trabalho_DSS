package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrcamentosLNFacade implements IOrcamentosLN {

    //Map<codOrcamento, Orcamento>
    private Map<String, Orcamento> porConfirmar = new HashMap<>();
    private Map<String, Orcamento> andamento = new HashMap<>();
    private Map<String, Orcamento> porPagar = new HashMap<>();
    private Map<String, Orcamento> pagos = new HashMap<>();
    private Map<String, Orcamento> recusados = new HashMap<>();

    //Map<codPedidoOrcamento, PedidoOrcamento>
    private Map<String, PedidoOrcamento> pedidos = new HashMap<>();

    //Map<produto, precoFixo>
    private Map<String, Double> precoFixos;
    int nextId = 0;


    public List<String> getOrcamentosDoTecnico(String codTecnico) {
        //TODO getOrcamentosDoTecnico
        return null;
    }
    public List<String> getOrcamentos() {
        //TODO getOrcamentos
        return null;
    }
    public Orcamento getOrcamento(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento r;
        r = this.porConfirmar.get(codOrcamento);
        if (r != null) return r.clone();

        r = this.andamento.get(codOrcamento);
        if (r != null) return r.clone();

        r = this.porPagar.get(codOrcamento);
        if (r != null) return r.clone();

        r = this.pagos.get(codOrcamento);
        if (r != null) return r.clone();

        r = this.recusados.get(codOrcamento);
        if (r != null) return r.clone();

        throw new OrcamentoInvalidoException();
    }
    public void registarPagamento(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = this.porPagar.remove(codOrcamento);
        if (o == null) throw new OrcamentoInvalidoException();
        this.pagos.put(o.getCodOrcamento(), o);
    }
    public void arquivarOrcamentoRecusado(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = this.porConfirmar.remove(codOrcamento);
        if (o == null) throw new OrcamentoInvalidoException();
        this.recusados.put(codOrcamento, o);
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
    public void registarOrcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = this.andamento.remove(codOrcamento);
        if (o == null) throw new OrcamentoInvalidoException();
        this.porPagar.put(o.getCodOrcamento(), o);
    }
    // Não me lembro do contexto desta função
    // Mas ela não deveria retornar alguma coisa?
    public void verificarCustoUltrapassado(String codOrcamennto) {
        //TODO  verificarCustoUltrapassado
    }
    // public codOrcamento txo(...);
    public String registarOrcamentoExpresso(String nif, String produto, String codPedidoOrcamento)
            throws PedidoOrcamentoInvalidoException, ProdutoInvalidoException {

        if (!this.precoFixos.containsKey(produto)) throw new ProdutoInvalidoException();

        PedidoOrcamento po = getPedidoOrcamento(codPedidoOrcamento);
        if (po instanceof PedidoOrcamentoFixo) {
            PedidoOrcamentoFixo pof = (PedidoOrcamentoFixo) po;
            String codTecnico = pof.getResponsavel();
            String codEquipamento = getEquipamentoPedido(codPedidoOrcamento);
            double precoFixo = this.precoFixos.get(produto);
            Orcamento o = new OrcamentoFixo(codTecnico, nif, codEquipamento, precoFixo ,produto);
            this.andamento.put(o.getCodOrcamento(), o);
            this.pedidos.remove(codPedidoOrcamento);

            return o.getCodOrcamento();
        } else throw new PedidoOrcamentoInvalidoException();

    }


    public void registarOrcamentoExpressoConcluido(String codOrcamento) {
        //TODO  registarOrcamentoFixoConcluido
    }
    public void adicionarPasso(String descricao, double previsaoTempo, double previsaoCusto, String codOrcamento, String passo) throws OrcamentoInvalidoException {
        Orcamento orcamento = this.porConfirmar.get(codOrcamento);
        if (orcamento instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) orcamento;
            orcamentoP.adicionarPasso(descricao,previsaoTempo, previsaoCusto, passo);
        } else throw new OrcamentoInvalidoException();
    }
    // Tinhamos concordado que os noPasso era uma String certo?
    public void assinalarPasso(double duracao, double custo, String passo, String codOrcamento)
            throws OrcamentoInvalidoException, PassoInvalidoException {
        Orcamento orcamento = this.andamento.get(codOrcamento);
        if (orcamento != null && orcamento instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) orcamento;
            orcamentoP.assinalarPasso(duracao,custo, passo);
        } else throw new OrcamentoInvalidoException();
    }
    public PlanoTrabalho getPlanoTrabalho(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = getOrcamento(codOrcamento);
        if (o instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) o;
            return orcamentoP.getPlano();
        } else throw new OrcamentoInvalidoException();
    }

    // Métodos relativos aos pedidos de orçamento
    public void registarPedidoOrcamento(String nif, String codEquipamento, String codFunc) {
        PedidoOrcamento pedido = new PedidoOrcamento(codEquipamento, LocalDateTime.now(), codFunc, nif);
        this.pedidos.put(pedido.getCodPedidoOrcamento(), pedido);
    }
    // public List<codPedidoOrcamento> getPedidosOrcamento();
    public List<String> getPedidosOrcamento() {
        return this.pedidos.keySet().stream().collect(Collectors.toList());
    }
    // public codEquipamento getEquipamentoPedido(String codPedidoOrcamento);
    public String getEquipamentoPedido(String codPedidoOrcamento) throws PedidoOrcamentoInvalidoException {
        PedidoOrcamento po = this.pedidos.get(codPedidoOrcamento);
        if (po == null) throw new PedidoOrcamentoInvalidoException();
        return po.getCodEquipamento();
    }
    public PedidoOrcamento getPedidoOrcamento(String codPedidoOrcamento) throws PedidoOrcamentoInvalidoException {
        PedidoOrcamento p = this.pedidos.get(codPedidoOrcamento);
        if (p == null) throw new PedidoOrcamentoInvalidoException();
        return p.clone();
    }
    public void registarPedidoOrcamentoExpresso(String nif, String codEquipamento, String codFunc, String codTecnico) {
        PedidoOrcamento pedido = new PedidoOrcamentoFixo(codEquipamento, LocalDateTime.now(), codFunc, nif, codTecnico);
        this.pedidos.put(pedido.getCodPedidoOrcamento(), pedido);
    }

    //public codEquipamento getCodEquipamentoOrcamento(String codOrcamento);
    public String getCodEquipamentoOrcamento(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = getOrcamento(codOrcamento);
        return o.getCodEquipamento();
    }
    public void registarOrcamentoAndamento(String codOrcamento) throws OrcamentoInvalidoException {
        Orcamento o = this.porConfirmar.remove(codOrcamento);
        if (o == null) throw new OrcamentoInvalidoException();
        this.andamento.put(o.getCodOrcamento(), o);
    }
    public void removePedidoOrcamento(String codPedidoOrcamento) {
        //TODO removePedidoOrcamento
    }

    @Override
    public boolean validarPedidoOrcamento(String codPedidoOrcamento) {
        PedidoOrcamento po = this.pedidos.get(codPedidoOrcamento);
        return (po != null);
    }

    public Passo getPasso(String codOrcamento, String passo) throws OrcamentoInvalidoException, PassoInvalidoException {
        Orcamento orcamento = getOrcamento(codOrcamento);
        if (orcamento != null && orcamento instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) orcamento;
            return orcamentoP.getPasso(passo);
        } else throw new OrcamentoInvalidoException();
    }

    public List<Passo> getSubPassos(String codOrcamento, String passo)
            throws OrcamentoInvalidoException, PassoInvalidoException, SemSubPassosException {
        Orcamento orcamento = getOrcamento(codOrcamento);
        if (orcamento != null && orcamento instanceof OrcamentoProgramado) {
            OrcamentoProgramado orcamentoP = (OrcamentoProgramado) orcamento;
            return orcamentoP.getSubPassos(passo);
        } else throw new OrcamentoInvalidoException();
    }

    public List<String> getOrcamentosPorConfirmar()  {
        return this.porConfirmar.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getOrcamentosAndamento() {
        return this.andamento.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getOrcamentosPorPagar() {
        return this.pagos.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getOrcamentosPagos() {
        return this.pagos.keySet().stream().collect(Collectors.toList());
    }
    public List<String> getOrcamentosRecusados()  {
        return this.recusados.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public boolean validaProduto(String produto) {
        return this.precoFixos.containsKey(produto);
    }
}
