package com.business;

import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.FuncionarioInvalidoException;
import com.business.Excecoes.OrcamentoInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;

import java.time.LocalDateTime;

public interface ICentroReparacoesLN {
    boolean isTecnicoDisponivel();
    //public codOrcamento registarOrcamentoProgramado(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
        throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException;
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double custoPecas, String codOrcamento, String passo)
        throws OrcamentoInvalidoException;
}
