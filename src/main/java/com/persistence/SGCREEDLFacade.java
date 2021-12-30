package com.persistence;

import com.business.CentroReparacoesLNFacade;

import java.io.*;

public class SGCREEDLFacade implements ISGCREEDL {
    private static final String nomeFicheiro = "/tmp/DB";

    public void saveState(CentroReparacoesLNFacade c) throws IOException{
        FileOutputStream fos = new FileOutputStream(nomeFicheiro, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(c);
        oos.flush();
        oos.close();
    }




}
