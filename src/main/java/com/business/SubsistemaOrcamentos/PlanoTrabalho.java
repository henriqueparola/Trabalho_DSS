package com.business.SubsistemaOrcamentos;

import com.business.Excecoes.PassoInvalidoException;
import com.business.Excecoes.SemSubPassosException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PlanoTrabalho {
    private Map<Integer, Passo> passos = new HashMap<>();

    public PlanoTrabalho(PlanoTrabalho p) {
        this.passos = p.getPassos();
    }
    public PlanoTrabalho() {
        this.passos = new HashMap<>();
    }

    public Map<Integer, Passo> getPassos() {
        Map<Integer, Passo> r = new HashMap<>();
        for (Passo p : this.passos.values())
            r.put(p.getNoPasso(), p.clone());
        return r;
    }

    public void adicionarPasso(String descricao, double previsaoTempo, double previsaoCusto, String passoUnparsed) {
        List<Integer> parsePassos = parsePasso(passoUnparsed);
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);

        if (passo == null) {
            passo = new Passo(previsaoTempo, previsaoCusto, descricao, nextPasso);
            this.passos.put(nextPasso, passo);
        }

        if (parsePassos.size() > 0)
            passo.adicionarPasso(descricao, previsaoTempo, previsaoCusto, parsePassos);

    }

    public void assinalarPasso(LocalDateTime duracao, double custo, String passoUnparsed) throws PassoInvalidoException {
        List<Integer> parsePassos = parsePasso(passoUnparsed);
        boolean passoValido = validarPasso(parsePassos);
        if (passoValido) {
            int nextPasso = parsePassos.remove(0);
            Passo passo = this.passos.get(nextPasso);
            passo.assinalarPasso(duracao,custo, parsePassos);
        }

    }

    private List<Integer> parsePasso(String passo) {
        if (passo.equals("")) return new ArrayList<>();
        final String delim = "#";
        String []tokens = passo.split(delim);
        List<Integer> parsedPassos = new ArrayList<>();
        for (String token : tokens)
            parsedPassos.add(Integer.parseInt(token));

        return parsedPassos;
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

    public Passo getPasso(String passoUnparsed) throws PassoInvalidoException {
        List<Integer> parsePassos = parsePasso(passoUnparsed);
        if (!validarPasso(parsePassos)) throw new PassoInvalidoException();
        int nextPasso = parsePassos.remove(0);
        Passo passo = this.passos.get(nextPasso);
        return passo.getPasso(parsePassos);
    }

    public List<Passo> getSubPassos(String passoUnparsed) throws PassoInvalidoException, SemSubPassosException {
        List<Integer> parsePassos = parsePasso(passoUnparsed);
        if (!validarPasso(parsePassos)) throw new PassoInvalidoException();
        if (parsePassos.size() > 0) {
            int nextPasso = parsePassos.remove(0);
            Passo passo = this.passos.get(nextPasso);
            return passo.getSubPassos(parsePassos);
        }
        List<Passo> firstLevel = passos.values().stream().map(Passo::clone).collect(Collectors.toList());
        if (firstLevel.size() == 0) throw new SemSubPassosException();
        return firstLevel;
    }

    public PlanoTrabalho clone() {
        return new PlanoTrabalho(this);
    }
}
