package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.SemSubPassosException;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Passo {
    private int noPasso;
    private boolean estadoConclusao;
    private double previsaoDuracao;
    private double duracao;
    private double previsaoCustoPecas;
    private double custoPecas;
    private String descricao;

    public Map<Integer, Passo> getPassos() {
        Map<Integer, Passo> r = new HashMap<>();
        for(Passo p : this.passos.values())
            r.put(p.getNoPasso(), p.clone());
        return r;
    }

    private Map<Integer, Passo> passos = new HashMap<>();

    public boolean isEstadoConclusao() {
        return estadoConclusao;
    }

    public void setEstadoConclusao(boolean estadoConclusao) {
        this.estadoConclusao = estadoConclusao;
    }

    public double getPrevisaoDuracao() {
        return previsaoDuracao;
    }

    public void setPrevisaoDuracao(double previsaoDuracao) {
        this.previsaoDuracao = previsaoDuracao;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public double getPrevisaoCustoPecas() {
        return previsaoCustoPecas;
    }

    public void setPrevisaoCustoPecas(double previsaoCustoPecas) {
        this.previsaoCustoPecas = previsaoCustoPecas;
    }

    public double getCustoPecas() {
        return custoPecas;
    }

    public void setCustoPecas(double custoPecas) {
        this.custoPecas = custoPecas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Passo(Passo p) {
        this.estadoConclusao = p.isEstadoConclusao();
        this.previsaoDuracao = p.getPrevisaoDuracao();
        this.duracao = p.getDuracao();
        this.previsaoCustoPecas = p.getPrevisaoCustoPecas();
        this.custoPecas = p.getCustoPecas();
        this.descricao = p.getDescricao();
        this.passos = p.getPassos();
        this.noPasso = p.getNoPasso();
    }

    public int getNoPasso() {
        return this.noPasso;
    }
    public Passo(double previsaoDuracao, double previsaoCustoPecas, String descricao, int noPasso) {
        this.previsaoDuracao = previsaoDuracao;
        this.previsaoCustoPecas = previsaoCustoPecas;
        this.descricao = descricao;
        this.noPasso = noPasso;
    }

    public void adicionarPasso(String descricao, double previsaoTempo, double previsaoCustoPecas,
                               List<Integer> parsePassos) {
        atualizarDados(previsaoTempo, previsaoCustoPecas);
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);

        if (passo == null) {
            passo = new Passo(previsaoTempo, previsaoCustoPecas, descricao, nextPasso);
            this.passos.put(nextPasso, passo);
        }

        if (parsePassos.size() > 0)
            passo.adicionarPasso(descricao, previsaoTempo, previsaoCustoPecas, parsePassos);

    }

    public void assinalarPasso(LocalDateTime duracao, double custo, List<Integer> parsePassos) {
        if (parsePassos.size() == 0) this.estadoConclusao = true;
        else {
            int nextPasso = parsePassos.remove(0);
            Passo passo = this.passos.get(nextPasso);
            passo.assinalarPasso(duracao, custo, parsePassos);
            boolean subPassos = verificarSubPassosConcluidos();
            if (subPassos) this.estadoConclusao = true;
        }
    }

    private boolean verificarSubPassosConcluidos() {
        for (Passo p : this.passos.values()) {
            if (p.estadoConclusao == false)
                return false;
        }

        return true;
    }

    private void atualizarDados(double previsaoTempo, double previsaoCustoPecas) {
        this.previsaoDuracao += previsaoTempo;
        this.previsaoCustoPecas += previsaoCustoPecas;
    }

    private void atualizarConfirmacoes(double duracao, double custo) {
        this.duracao += duracao;
        this.custoPecas += custo;
    }


    public boolean validarPasso(List<Integer> parsePassos) {
        if (parsePassos.size() == 0) return true;
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);
        try {
            if (passo != null)
                return passo.validarPasso(parsePassos);
            else return false;
        } finally {
            parsePassos.add(0, nextPasso);
        }
    }

    public Passo getPasso(List<Integer> parsePassos) {
        if (parsePassos.size() == 0) return clone();
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);
        return passo.getPasso(parsePassos);
    }

    public List<Passo> getSubPassos(List<Integer> parsePassos) throws SemSubPassosException {
        if (parsePassos.size() == 0) {
            List<Passo> res = this.passos.values().stream().map(Passo::clone).collect(Collectors.toList());
            if (res.size() == 0) throw new SemSubPassosException();
            return res;
        }
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);
        return passo.getSubPassos(parsePassos);
    }

    public Passo clone() {
        return new Passo(this);
    }

}
