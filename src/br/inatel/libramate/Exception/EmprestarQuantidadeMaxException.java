package br.inatel.libramate.Exception;

public class EmprestarQuantidadeMaxException extends Exception {
    public EmprestarQuantidadeMaxException(String nome) {
        super("A pessoa " + nome + " já atingiu o limite de livros emprestados.");
    }
}
