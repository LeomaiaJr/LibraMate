package br.inatel.libramate.FileController;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import br.inatel.libramate.Model.Livro;

public class LivroFileController extends GenericFileController<Livro> {
    @Override
    public String getFileName() {
        return "livro.txt";
    }

    @Override
    public String getSeparator() {
        return "###LIVRO###";
    }

    @Override
    public void deserialize(BufferedWriter bw, Livro obj) {
        try {
            bw.write(obj.getTitulo() + "\n");
            bw.write(obj.getAutor() + "\n");
            bw.write(obj.getGeneroLiterario() + "\n");
            bw.write(obj.getQtdFolhas() + "\n");
            bw.write(obj.getEditora() + "\n");
            bw.write(obj.getAnoLancamento() + "\n");
            bw.write(obj.getEmprestado() + "\n");
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }

    @Override
    public Livro serialize(BufferedReader br) {
        try {
            String titulo = br.readLine();
            String autor = br.readLine();
            String generoLiterario = br.readLine();
            int qtdFolhas = Integer.parseInt(br.readLine());
            String editora = br.readLine();
            int anoLancamento = Integer.parseInt(br.readLine());
            boolean emprestado = Boolean.parseBoolean(br.readLine());

            return new Livro(titulo, autor, generoLiterario, qtdFolhas, editora, anoLancamento, emprestado);
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }
}
