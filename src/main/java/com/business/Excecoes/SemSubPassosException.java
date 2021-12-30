package com.business.Excecoes;

import java.io.Serializable;

public class SemSubPassosException extends Exception implements Serializable {
    public SemSubPassosException() {
        super();
    }

    public SemSubPassosException(String message) {
        super(message);
    }
}
