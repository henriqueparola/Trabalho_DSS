package com.business;

import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.FuncionarioInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;

public interface ICentroReparacoesLN {
    boolean isTecnicoDisponivel();
    //public codOrcamento registarOrcamentoProgramado(...);
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
        throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException;
}
