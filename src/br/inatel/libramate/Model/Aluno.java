package br.inatel.libramate.Model;

public class Aluno extends Pessoa {
    private int numeroMatricula;
    private static int quantidadeMaximaLivros = 2;

    public Aluno(String nome, String cpf, int numeroMatricula) {
        super(nome, cpf, quantidadeMaximaLivros);
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public void showInfo() {
        System.out.println("====ALUNO====");
        System.out.println("Numero de Matricula: " + numeroMatricula);
        super.showInfo();
        System.out.println("==============");
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public int getQuantidadeMaximaLivros() {
        return quantidadeMaximaLivros;
    }
}
