package main.java.com.business.SubsistemaFuncionarios;

import main.java.com.business.Excecoes.FuncionarioInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FuncionarioLNFacade implements IFuncionarioLN {

    //Map<codFuncionario, Funcionario>
    private Map<String, Funcionario> funcionarios;


    //public List<codFunc> getTecnicos();
    public List<String> getTecnicos() {
        List<String> tecnicos = new ArrayList<>();
        for(Funcionario f : funcionarios.values())
            if (f instanceof Tecnico)
                tecnicos.add(f.getCodFunc());

        return tecnicos;
    }
    public Tecnico getTecnico(String codTecnico) throws FuncionarioInvalidoException {
        Funcionario f = funcionarios.get(codTecnico);
        if (f == null || !(f instanceof Tecnico)) throw new FuncionarioInvalidoException();

        Funcionario cloned = f.clone();
        Tecnico res = (Tecnico) cloned;
        return res;
    }
    //public List<codFunc> getFuncionariosBalcao();
    public List<String> getFuncionariosBalcao() {
        List<String> balcao = new ArrayList<>();
        for(Funcionario f : funcionarios.values())
            if (f instanceof FuncBalcao)
                balcao.add(f.getCodFunc());

        return balcao;
    }

    //public List<codPedidoOrcamento>
    // Esta função engloba outro subsistema
    // É necessária faze-la na SGCREE
    public List<String> getDadosEntregaDoFuncionario(String codFuncionario) throws FuncionarioInvalidoException {
        //TODO getDadosEntregaDoFuncionario
        return null;
    }
    public FuncBalcao getFuncBalcao(String codFunc) throws FuncionarioInvalidoException {
        Funcionario f = funcionarios.get(codFunc);
        if (f == null || !(f instanceof FuncBalcao)) throw new FuncionarioInvalidoException();

        Funcionario cloned = f.clone();
        FuncBalcao res = (FuncBalcao) cloned;
        return res;
    }
}
