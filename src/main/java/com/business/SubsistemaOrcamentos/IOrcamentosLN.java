package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.Excecoes.PassoInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;
import com.business.Excecoes.SemSubPassosException;
import com.business.Excecoes.ProdutoInvalidoException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrcamentosLN  {
    public List<String> getOrcamentosDoTecnico(String codTecnico);
    // public List<codOrcamento> getOrcamentos();
    public List<String> getOrcamentos();
    public Orcamento getOrcamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void registarPagamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void arquivarOrcamentoRecusado(String codOrcamento) throws OrcamentoInvalidoException;
    public void arquivarOrcamentosSemConfirmacao();
    // public codOrcamento registarOrcamento(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codEquipamento);
    public void registarOrcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException;
    // Não me lembro do contexto desta função
    // Mas ela não deveria retornar alguma coisa?
    public boolean verificarCustoUltrapassado(String codOrcamento) throws OrcamentoInvalidoException;
    // public codOrcamento registarOrcamentoFixo(...);
    public String registarOrcamentoExpresso(String nif, String produto, String codPedidoOrcamento) throws PedidoOrcamentoInvalidoException, ProdutoInvalidoException;
    public void adicionarPasso(String descricao, double previsaoTempo, double previsaoCusto, String codOrcamento, String passo)
            throws OrcamentoInvalidoException;
    // Tinhamos concordado que os noPasso era uma String certo?
    public void assinalarPasso(double duracao, double custo, String passo, String codOrcamente)
            throws OrcamentoInvalidoException, PassoInvalidoException;
    public PlanoTrabalho getPlanoTrabalho(String codOrcamento) throws OrcamentoInvalidoException;

    // Métodos relativos aos pedidos de orçamento
    public void registarPedidoOrcamento(String nif, String nomeEquipamento, String codFunc);
    // public List<codPedidoOrcamento> getPedidosOrcamento();
    public List<String> getPedidosOrcamento();
    // public codEquipamento getEquipamentoPedido(String codPedidoOrcamento);
    public PedidoOrcamento getPedidoOrcamento(String codPedidoOrcamento) throws PedidoOrcamentoInvalidoException;
    public String getEquipamentoPedido(String codPedidoOrcamento) throws PedidoOrcamentoInvalidoException;
    public void registarPedidoOrcamentoExpresso(String nif, String codEquipamento, String codFunc, String codTecnico);
    //public codEquipamento getCodEquipamentoOrcamento(String codOrcamento);
    public String getCodEquipamentoOrcamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void registarOrcamentoAndamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void removePedidoOrcamento(String codPedidoOrcamento);
    public boolean validarPedidoOrcamento(String codPedidoOrcamento);
    public Passo getPasso(String codOrcamento, String passo) throws OrcamentoInvalidoException, PassoInvalidoException;
    public List<Passo> getSubPassos(String codOrcamento, String passo)
            throws OrcamentoInvalidoException, PassoInvalidoException, SemSubPassosException;
    public List<String> getOrcamentosPorConfirmar();
    public List<String> getOrcamentosAndamento();
    public List<String> getOrcamentosPorPagar();
    public List<String> getOrcamentosPagos();
    public List<String> getOrcamentosRecusados();
    public boolean validaProduto(String produto);
    public OrcamentoProgramado getOrcamentoProgramado(String codOrcamento) throws OrcamentoInvalidoException;
    public boolean orcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException;
}
