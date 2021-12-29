package com.business;

import com.business.Excecoes.*;
import com.business.SubsistemaClientes.Cliente;
import com.business.SubsistemaEquipamentos.Equipamento;
import com.business.SubsistemaOrcamentos.Orcamento;
import com.business.SubsistemaOrcamentos.Passo;
import com.business.SubsistemaOrcamentos.PedidoOrcamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface ICentroReparacoesLN {

    public List<String> getPedidosOrcamento();
    public PedidoOrcamento getPedidoOrcamento(String codPedido) throws PedidoOrcamentoInvalidoException;
    //public codOrcamento registarOrcamentoProgramado(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
        throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException;
    public String registarOrcamentoExpresso(String nif, String produto, String codPedidoOrcamento)
            throws PedidoOrcamentoInvalidoException, ProdutoInvalidoException;
    public void adicionarPasso(String descricao, double previsaoTempo, double custoPecas, String codOrcamento, String passo)
        throws OrcamentoInvalidoException;
    public void assinalarPasso(double duracao, double custoReal, String passo, String codOrcamento)
            throws OrcamentoInvalidoException, PassoInvalidoException;
    public Passo getPasso(String codOrcamento, String passo) throws OrcamentoInvalidoException, PassoInvalidoException;
    public List<Passo> getSubPassos(String codOrcamento, String passo)
            throws OrcamentoInvalidoException, PassoInvalidoException, SemSubPassosException;
    public void registarPedidoOrcamento(String nif, String nomeEquipamento, String codFunc) throws ClienteInvalidoException, FuncionarioInvalidoException;
    public List<String> getOrcamentosPorConfirmar();
    public List<String> getOrcamentosAndamento();
    public List<String> getOrcamentosPorPagar();
    public List<String> getOrcamentosPagos();
    public List<String> getOrcamentosRecusados();
    public Orcamento getOrcamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void arquivarOrcamentoRecusado(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException;
    public void registarPagamento(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException;
    public void registarOrcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException;
    public void registarPedidoOrcamentoExpresso(String nif, String nomeEquipamento, String codFunc, String codTecnico)
            throws ClienteInvalidoException, FuncionarioInvalidoException, ProdutoInvalidoException;
    public boolean validarCliente(String nif);
    public void registarCliente(String nif, String nome, String email, String telemovel);
    // returns List<codCliente>
    public List<String> getClientes();
    public Cliente getCliente(String codCliente) throws ClienteInvalidoException;
    public List<String> getEquipamentosAndamento();
    public List<String> getEquipamentosPago();
    public List<String> getEquipamentosPorPagar();
    public List<String> getEquipamentosRecusado();
    public List<String> getEquipamentosAbandonado();
    public Equipamento getEquipamento(String codEquipamento) throws EquipamentoInvalidoException;
    public void registarOrcamentoAndamento(String codOrcamento) throws OrcamentoInvalidoException;
    public void enviarEmailConfirmacao(String codOrcamento) throws OrcamentoInvalidoException;
    public void enviarEmailConclusao(String codOrcamento) throws OrcamentoInvalidoException;
    boolean isTecnicoDisponivel();
    boolean orcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException;
    public void registarOrcamentoExpressoConcluido(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException;




}
