package main.java.com.business;

import java.time.LocalDateTime;
import java.util.List;

public interface IGestOrcamento {
    public List<String> getOrcamentosDoTecnico(String codTecnico);
    public List<String> getOrcamentos();
    public Orcamento getOrcamento(String codOrcamento);
    public void registarPagamento(String codOrcamento);
    public void arquivarOrcamentoRecusado(String codOrcamento);
    public void arquivarOrcamentosSemConfirmacao();
    // public codOrcamento registarOrcamento(...);
    public String registarOrcamento(String nif, String codTecnico, String codPlano, String PedidoOrcamento);
    public void registarOrcamentoConcluido(String codOrcamento);
    // Não me lembro do contexto desta função
    // Mas ela não deveria retornar alguma coisa?
    public void verificarCustoUltrapassado(String codOrcamennto);
    // public codOrcamento registarOrcamentoFixo(...);
    public String registarOrcamentoFixo(String nif, String produto, String codPedidoOrcamento);
    public void registarOrcamentoFixoConcluido(String codOrcamento);
    // public codPlano registarPlanoTrabalho();
    public String registarPlanoTrabalho();
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCusto, String codPlano);
    // Tinhamos concordado que os noPasso era uma String certo?
    public void assinalarPasso(LocalDateTime duracao, double custo, String noPasso, String codPlanoTrabalho);
    public PlanoTrabalho getPlanoTrabalho(String codPlanoTrabalho);

    // Métodos relativos aos pedidos de orçamento
    public void registarPedidoOrcamento(String nif, String nomeEquipamento, String codFunc);
    // public List<codPedidoOrcamento> getPedidosOrcamento();
    public List<String> getPedidosOrcamento();
    // public codEquipamento getEquipamentoPedido(String codPedidoOrcamento);
    public String getEquipamentoPedido(String codPedidoOrcamento);
    public PedidoOrcamento getPedidoOrcamento(String codPedidoOrcamento);
    public void registarPedidoOrcamentoExpresso(String nif, String nomeEquipamento, String codFunc, String codTecnico);


}
