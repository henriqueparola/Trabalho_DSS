package com.business;

import com.business.Excecoes.*;
import com.business.SubsistemaClientes.ClienteLNFacade;
import com.business.SubsistemaClientes.IClienteLN;
import com.business.SubsistemaEquipamentos.EquipamentoLNFacade;
import com.business.SubsistemaEquipamentos.IEquipamentoLN;
import com.business.SubsistemaFuncionarios.FuncionarioLNFacade;
import com.business.SubsistemaFuncionarios.IFuncionarioLN;
import com.business.SubsistemaOrcamentos.IOrcamentosLN;
import com.business.SubsistemaOrcamentos.OrcamentosLNFacade;

import java.time.LocalDateTime;

public class CentroReparacoesLNFacade implements ICentroReparacoesLN {
    IClienteLN clienteLN = new ClienteLNFacade();
    IEquipamentoLN equipamentoLN = new EquipamentoLNFacade();
    IFuncionarioLN funcionarioLN = new FuncionarioLNFacade();
    IOrcamentosLN orcamentosLN = new OrcamentosLNFacade();

    public boolean isTecnicoDisponivel() {
        return false;
    }

    //returns codOrcamento
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
            throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException {
        boolean nifValido = clienteLN.validarCliente(nif);
        boolean tecnicoValido = funcionarioLN.validarTecnico(codTecnico);
        boolean pedidoOrcamentoValido = orcamentosLN.validarPedidoOrcamento(codPedidoOrcamento);

        if (nifValido && tecnicoValido && pedidoOrcamentoValido) {
        String codEquipamento = orcamentosLN.getEquipamentoPedido(codPedidoOrcamento);

        return orcamentosLN.registarOrcamentoProgramado(nif, codTecnico, codEquipamento);
        }
        else {
                // Eu sei que ele vai fazer throw da primeira condição que der falsa
                // mas assim sempre dá para perceber qual o problema exato
            if (!nifValido) throw new ClienteInvalidoException();
            else if (!tecnicoValido) throw new FuncionarioInvalidoException();
            else throw new PedidoOrcamentoInvalidoException();
        }
    }

    @Override
    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double custoPecas, String codOrcamento, String passo)
            throws OrcamentoInvalidoException {
        orcamentosLN.adicionarPasso(descricao, previsaoTempo, custoPecas, codOrcamento, passo);
    }

    @Override
    public void assinalarPasso(LocalDateTime duracao, double custoReal, String passo, String codOrcamento)
            throws OrcamentoInvalidoException, PassoInvalidoException {
        orcamentosLN.assinalarPasso(duracao,custoReal,passo,codOrcamento);
    }
}

