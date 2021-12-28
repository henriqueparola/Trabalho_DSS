package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrcamentosLNFacade implements IOrcamentosLN {

    //Map<codOrcamento, Orcamento>
    private Map<String, Orcamento> porConfirmar;
    private Map<String, Orcamento> andamento;
    private Map<String, Orcamento> porPagar;
    private Map<String, Orcamento> pagos;
    private Map<String, Orcamento> arquivados;

    //Map<codPedidoOrcamento, PedidoOrcamento>
    private Map<String, PedidoOrcamento> pedidos;

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

        r = this.arquivados.get(codOrcamento);
        if (r != null) return r.clone();

        throw new OrcamentoInvalidoException();
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
        //TODO remover do pedido
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
    public String registarOrcamentoFixo(String nif, String produto, String codPedidoOrcamento)
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

            return o.getCodOrcamento();
        } else throw new PedidoOrcamentoInvalidoException();

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
    public void assinalarPasso(LocalDateTime duracao, double custo, String passo, String codOrcamento)
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
    public List<String> getOrcamentosArquivados()  {
        return this.arquivados.keySet().stream().collect(Collectors.toList());
    }
}
