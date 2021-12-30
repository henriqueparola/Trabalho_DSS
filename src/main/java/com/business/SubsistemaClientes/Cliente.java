package com.business.SubsistemaClientes;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nif;
    private String nome;
    private String email;
    private String telemovel;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public Cliente(String nif, String nome, String email, String telemovel) {
        this.nif = nif;
        this.nome = nome;
        this.email = email;
        this.telemovel = telemovel;
    }

    public Cliente(Cliente c) {
        this.nif = c.getNif();
        this.nome = c.getNome();
        this.email = c.getEmail();
        this.telemovel = c.getTelemovel();
    }

    public Cliente clone() {
        return new Cliente(this);
    }
}
