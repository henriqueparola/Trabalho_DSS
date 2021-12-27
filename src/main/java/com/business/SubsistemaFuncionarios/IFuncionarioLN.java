package com.business.SubsistemaFuncionarios;

import com.business.Excecoes.FuncionarioInvalidoException;

import java.util.List;

public interface IFuncionarioLN {
    //public List<codFunc> getTecnicos();
    public List<String> getTecnicos();
    public Tecnico getTecnico(String codTecnico) throws FuncionarioInvalidoException;
    //public List<codFunc> getFuncionariosBalcao();
    public List<String> getFuncionariosBalcao();
    //public List<codPedidoOrcamento>
    public List<String> getDadosEntregaDoFuncionario(String codFuncionario) throws FuncionarioInvalidoException;
    public FuncBalcao getFuncBalcao(String codFunc) throws FuncionarioInvalidoException;
}
