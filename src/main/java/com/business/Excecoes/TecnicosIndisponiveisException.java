package com.business.Excecoes;

import java.io.Serializable;

public class TecnicosIndisponiveisException extends Exception implements Serializable {
    public TecnicosIndisponiveisException() {
        super();
    }

    public TecnicosIndisponiveisException(String message) {
        super(message);
    }
}
