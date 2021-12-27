package com.business.SubsistemaOrcamentos;

import java.time.LocalDateTime;
import java.util.*;

public class PlanoTrabalho {
    private Map<Integer, Passo> passos;

    public PlanoTrabalho() {
        this.passos = new HashMap<>();
    }

    public void adicionarPasso(String descricao, LocalDateTime previsaoTempo, double previsaoCusto, String passoUnparsed) {
        List<Integer> parsePassos = parsePasso(passoUnparsed);
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);

        if (passo == null) {
            passo = new Passo(previsaoTempo, previsaoCusto, descricao);
            this.passos.put(nextPasso, passo);
        }

        if (parsePassos.size() > 0)
            passo.adicionarPasso(descricao, previsaoTempo, previsaoCusto, parsePassos);

    }

    private List<Integer> parsePasso(String passo) {
        final String delim = "#";
        String []tokens = passo.split(delim);
        List<Integer> parsedPassos = new ArrayList<>();
        for (String token : tokens)
            parsedPassos.add(Integer.parseInt(token));

        return parsedPassos;
    }
}
