package com.business;

import com.business.Excecoes.*;
import com.business.SubsistemaOrcamentos.Passo;
import com.sun.istack.internal.localization.LocalizableMessageFactory;

import java.time.LocalDateTime;
import java.util.List;

public interface ICentroReparacoesLN {
    boolean isTecnicoDisponivel();
    //public codOrcamento registarOrcamentoProgramado(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
        throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException;
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double custoPecas, String codOrcamento, String passo)
        throws OrcamentoInvalidoException;
    public void assinalarPasso(LocalDateTime duracao, double custoReal, String passo, String codOrcamento)
            throws OrcamentoInvalidoException, PassoInvalidoException;
    public Passo getPasso(String codOrcamento, String passo) throws OrcamentoInvalidoException, PassoInvalidoException;
    public List<Passo> getSubPassos(String codOrcamento, String passo)
            throws OrcamentoInvalidoException, PassoInvalidoException, SemSubPassosException;


}
