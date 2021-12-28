package com.business.SubsistemaOrcamentos;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Map;

public class Passo {
    private boolean estadoConclusao;
    private LocalDateTime previsaoDuracao;
    private LocalDateTime duracao;
    private double previsaoCustoPecas;
    private double custoPecas;
    private String descricao;
    private Map<Integer, Passo> passos;


    public Passo(LocalDateTime previsaoDuracao, double previsaoCustoPecas, String descricao) {
        this.previsaoDuracao = previsaoDuracao;
        this.previsaoCustoPecas = previsaoCustoPecas;
        this.descricao = descricao;
    }

    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCustoPecas,
                               List<Integer> parsePassos) {
        atualizarDados(previsaoTempo, previsaoCustoPecas);
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);

        if (passo == null) {
            passo = new Passo(previsaoTempo, previsaoCustoPecas, descricao);
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

    private void atualizarDados(LocalDateTime previsaoTempo, double previsaoCustoPecas) {
        this.previsaoDuracao.plusMonths(previsaoTempo.getMonthValue());
        this.previsaoDuracao.plusDays(previsaoTempo.getDayOfMonth());
        this.previsaoDuracao.plusHours(previsaoTempo.getHour());
        this.previsaoDuracao.plusMinutes(previsaoTempo.getMinute());
        this.previsaoDuracao.plusSeconds(previsaoTempo.getSecond());
        this.previsaoCustoPecas += previsaoCustoPecas;
    }

    private void atualizarConfirmacoes(LocalDateTime duracao, double custo) {
        this.duracao.plusMonths(duracao.getMonthValue());
        this.duracao.plusDays(duracao.getDayOfMonth());
        this.duracao.plusHours(duracao.getHour());
        this.duracao.plusMinutes(duracao.getMinute());
        this.duracao.plusSeconds(duracao.getSecond());
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


}
