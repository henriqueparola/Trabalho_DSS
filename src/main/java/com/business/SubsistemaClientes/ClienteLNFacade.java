package main.java.com.business.SubsistemaClientes;

import java.util.Map;

public class ClienteLNFacade implements IClienteLN {

    //Map<codCLiente, Cliente>
    private Map<String, Cliente> clientes;


    public boolean validarCliente(String nif) {
        Cliente c = clientes.get(nif);
        return (c != null);
    }
    public void registarCliente(String nif, String nome, String email, String telemovel) {
        Cliente c = new Cliente(nif, nome, email, telemovel);
        clientes.put(nif, c);
    }
}
