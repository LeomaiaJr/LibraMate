package br.inatel.libramate.Exception;

public class LivroDuplicadoException extends Exception {

    public LivroDuplicadoException(String titulo) {
        super("Um livro com o título \"" + titulo + "\" já existe.");
    }
}
