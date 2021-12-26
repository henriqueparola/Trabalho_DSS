package main.java.com.business.Excecoes;

public class FuncionarioNaoValidoException extends Exception {
    public FuncionarioNaoValidoException() {
        super();
    }

    public FuncionarioNaoValidoException(String message) {
        super(message);
    }
}
