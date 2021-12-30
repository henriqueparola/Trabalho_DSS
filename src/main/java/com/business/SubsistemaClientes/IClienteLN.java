package com.business.SubsistemaClientes;

import com.business.Excecoes.ClienteInvalidoException;

import java.io.Serializable;
import java.util.List;

public interface IClienteLN  {
    public boolean validarCliente(String nif);
    public void registarCliente(String nif, String nome, String email, String telemovel) throws ClienteInvalidoException;
    // returns List<codCliente>
    public List<String> getClientes();
    public Cliente getCliente(String codCliente) throws ClienteInvalidoException;
}
