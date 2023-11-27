package br.inatel.libramate.Model;

public class Professor extends Pessoa {
    private int numeroRegistro;
    private static int quantidadeMaximaLivros = 5;

    public Professor(String nome, String cpf, int numeroRegistro) {
        super(nome, cpf, quantidadeMaximaLivros);
        this.numeroRegistro = numeroRegistro;
    }

    @Override
    public void showInfo() {
        System.out.println("====Professor====");
        System.out.println("Código: " + numeroRegistro);
        super.showInfo();
        System.out.println("=====================");
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    @Override
    public int getQuantidadeMaximaLivros() {
        return quantidadeMaximaLivros;
    }
}
