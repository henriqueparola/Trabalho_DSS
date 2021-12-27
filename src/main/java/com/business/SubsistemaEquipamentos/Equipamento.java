package main.java.com.business.SubsistemaEquipamentos;

public class Equipamento {
    private String codEquipamento;
    private String nomeEquipamento;
    private String nifCliente;

    public Equipamento(String nomeEquipamento, String nifCliente, String codEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
        this.nifCliente = nifCliente;
        this.codEquipamento = codEquipamento;
    }
}
