package com.business;

import com.business.Excecoes.ClienteInvalidoException;
import com.business.Excecoes.FuncionarioInvalidoException;
import com.business.Excecoes.PedidoOrcamentoInvalidoException;

public class ISGCREEFacade implements ISGCREE{
    @Override
    public boolean isTecnicoDisponivel() {
        return false;
    }

    //returns codOrcamento
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
            throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException {


        return null;
    }
}

