package main.java.com.business.SubsistemaClientes;

public interface IClienteLN {
    public boolean validarCliente(String nif);
    public void registarCliente(String nif, String email, String telemovel);
}
