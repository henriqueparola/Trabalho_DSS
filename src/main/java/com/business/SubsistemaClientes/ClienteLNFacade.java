package com.business.SubsistemaClientes;

import com.business.Excecoes.ClienteInvalidoException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteLNFacade implements IClienteLN, Serializable {

    //Map<codCLiente, Cliente>
    private Map<String, Cliente> clientes = new HashMap<>();

    public boolean validarCliente(String nif) {
        Cliente c = clientes.get(nif);
        return (c != null);
    }
    public void registarCliente(String nif, String nome, String email, String telemovel) throws ClienteInvalidoException{
        Cliente c = new Cliente(nif, nome, email, telemovel);
        if (validarCliente(nif)) throw new ClienteInvalidoException();
        else {
            clientes.put(nif, c);
        }
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

