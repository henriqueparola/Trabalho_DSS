package com.business.SubsistemaClientes;

public interface IClienteLN {
    public boolean validarCliente(String nif);
    public void registarCliente(String nif, String nome, String email, String telemovel);
}
