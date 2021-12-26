package main.java.com.business.SubsistemaOrcamentos;

import main.java.com.business.Orcamento;
import main.java.com.business.PedidoOrcamento;
import main.java.com.business.PlanoTrabalho;
import main.java.com.business.SubsistemaOrcamentos.IOrcamentosLN;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrcamentosLNFacade implements IOrcamentosLN {

    //Map<codOrcamento, Orcamento>
    private Map<String, Orcamento> andamento;
    private Map<String, Orcamento> porPagar;
    private Map<String, Orcamento> pagos;
    private Map<String, Orcamento> arquivados;

    //Map<codPedidoOrcamento, PedidoOrcamento>
    private Map<String, PedidoOrcamento> pedidos;


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
    public String registarOrcamentoProgramado(String nif, String codTecnico, String PedidoOrcamento) {
        //TODO  registarOrcamento
        return null;
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
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCusto, String codOrcamento, String passo) {
        //TODO  adicionarPasso
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
}
