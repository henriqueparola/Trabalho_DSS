package main.java.com.business;

public interface IClienteLN {
    public boolean validarCliente(String nif);
    public void registarCliente(String nif, String email, String telemovel);
}
