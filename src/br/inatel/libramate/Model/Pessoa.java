package br.inatel.libramate.Model;

import java.util.ArrayList;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int quantidadeMaximaLivros;

    private ArrayList<Livro> livros = new ArrayList<Livro>();
    private ArrayList<String> titulos = new ArrayList<String>();

    public Pessoa(String nome, String cpf, int quantidadeMaximaLivros) {
        this.nome = nome;
        this.cpf = cpf;
        this.quantidadeMaximaLivros = quantidadeMaximaLivros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(ArrayList<String> titulos) {
        this.titulos = titulos;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public void showInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);

        if (livros.size() > 0) {
            System.out.println("Livros: ");
            for (Livro livro : livros) {
                livro.showInfo();
            }
        }
    }

    public abstract int getQuantidadeMaximaLivros();
}
