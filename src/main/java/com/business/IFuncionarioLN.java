package main.java.com.business;

import main.java.com.business.Excecoes.FuncionarioNaoValidoException;

import java.util.List;

public interface IFuncionarioLN {
    //public List<codFunc> getTecnicos();
    public List<String> getTecnicos();
    public Tecnico getTecnico(String codTecnico);
    //public List<codFunc> getFuncionariosBalcao();
    public List<String> getFuncionariosBalcao();
    //public List<codPedidoOrcamento>
    public List<String> getDadosEntregaDoFuncionario(String codFuncionario) throws FuncionarioNaoValidoException;
    public FuncBalcao getFuncBalcao(String codFunc);
}
