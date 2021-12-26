package main.java.com.business.SubsistemaClientes;

import java.util.Map;

public class ClienteLNFacade implements IClienteLN {

    //Map<codCLiente, Cliente>
    private Map<String, Cliente> clientes;


    public boolean validarCliente(String nif) {
        //TODO validarCliente
        return true;
    }
    public void registarCliente(String nif, String email, String telemovel) {
        //TODO registrarCliente
    }
}
