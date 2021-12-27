package com.business.SubsistemaClientes;

public class Cliente {
    private String nif;
    private String nome;
    private String email;
    private String telemovel;

    public Cliente(String nif, String nome, String email, String telemovel) {
        this.nif = nif;
        this.nome = nome;
        this.email = email;
        this.telemovel = telemovel;
    }
}
