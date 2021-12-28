package com.business.SubsistemaClientes;

import com.business.Excecoes.ClienteInvalidoException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<String> getClientes() {
        return this.clientes.keySet().stream().collect(Collectors.toList());
    }

    public Cliente getCliente(String codCliente) throws ClienteInvalidoException {
        Cliente r = this.clientes.get(codCliente);
        if (r == null) throw new ClienteInvalidoException();
        return r.clone();
    }
}

