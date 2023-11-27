package br.inatel.libramate.Model;

public class Livro {
    private String titulo;
    private String autor;
    private String generoLiterario;
    private int qtdFolhas;
    private String editora;
    private int anoLancamento;
    private boolean emprestado = false;

    public Livro(String titulo, String autor, String generoLiterario, int qtdFolhas, String editora,
            int anoLancamento, boolean emprestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.generoLiterario = generoLiterario;
        this.qtdFolhas = qtdFolhas;
        this.editora = editora;
        this.anoLancamento = anoLancamento;
        this.emprestado = emprestado;
    }

    public void showInfo() {
        System.out.println("====LIVRO====");
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Genero Literario: " + generoLiterario);
        System.out.println("Quantidade de Folhas: " + qtdFolhas);
        System.out.println("Editora: " + editora);
        System.out.println("Ano de Lancamento: " + anoLancamento);
        System.out.println("Emprestado: " + (emprestado ? "Sim" : "Não"));
        System.out.println("==============");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGeneroLiterario() {
        return generoLiterario;
    }

    public void setGeneroLiterario(String generoLiterario) {
        this.generoLiterario = generoLiterario;
    }

    public int getQtdFolhas() {
        return qtdFolhas;
    }

    public void setQtdFolhas(int qtdFolhas) {
        this.qtdFolhas = qtdFolhas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean getEmprestado() {
        return emprestado;
    }

}
