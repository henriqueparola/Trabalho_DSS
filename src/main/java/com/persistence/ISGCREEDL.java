package com.persistence;

import com.business.CentroReparacoesLNFacade;

import java.io.*;

public interface ISGCREEDL {
    public final static String nomeFicheiro = "/tmp/DB";
    public static CentroReparacoesLNFacade loadState() throws IOException, ClassNotFoundException {
            FileInputStream fis = new FileInputStream(nomeFicheiro);
            ObjectInputStream ois = new ObjectInputStream(fis);
            CentroReparacoesLNFacade r = (CentroReparacoesLNFacade) ois.readObject();
            ois.close();
            return r;
    }


    public void saveState(CentroReparacoesLNFacade centroReparacoesLNFacade) throws IOException;
}
