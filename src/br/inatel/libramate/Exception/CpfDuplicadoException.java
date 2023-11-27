package br.inatel.libramate.Exception;

public class CpfDuplicadoException extends Exception {

    public CpfDuplicadoException(String cpf) {
        super("Uma pessoa com o CPF " + cpf + " já existe.");
    }
}