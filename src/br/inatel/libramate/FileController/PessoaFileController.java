package br.inatel.libramate.FileController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

import br.inatel.libramate.Model.Pessoa;
import br.inatel.libramate.Model.Aluno;
import br.inatel.libramate.Model.Livro;
import br.inatel.libramate.Model.Professor;

public class PessoaFileController extends GenericFileController<Pessoa> {
    @Override
    public String getFileName() {
        return "pessoa.txt";
    }

    @Override
    public String getSeparator() {
        return "###PESSOA###";
    }

    @Override
    public void deserialize(BufferedWriter bw, Pessoa obj) {
        try {
            String tipo = obj instanceof Aluno ? "Aluno" : "Professor";
            int identificador = obj instanceof Aluno ? ((Aluno) obj).getNumeroMatricula()
                    : ((Professor) obj).getNumeroRegistro();

            bw.write(obj.getNome() + "\n");
            bw.write(obj.getCpf() + "\n");
            bw.write(tipo + "\n");
            bw.write(identificador + "\n");

            ArrayList<Livro> livros = obj.getLivros();
            String livroTitulo = "";
            for (Livro livro : livros) {
                livroTitulo += livro.getTitulo() + ";";
            }
            bw.write(livroTitulo + "\n");

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }

    @Override
    public Pessoa serialize(BufferedReader br) {
        try {
            String nome = br.readLine();
            String cpf = br.readLine();
            String tipo = br.readLine();
            int identificador = Integer.parseInt(br.readLine());

            String livroTitulo = br.readLine();
            ArrayList<String> livros = new ArrayList<String>();
            String[] livrosSplit = livroTitulo.split(";");
            for (String livro : livrosSplit) {
                if (livro.length() > 0)
                    livros.add(livro);
            }

            if (tipo.equals("Aluno")) {
                Aluno aluno = new Aluno(nome, cpf, identificador);
                aluno.setTitulos(livros);

                return aluno;
            } else {
                Professor professor = new Professor(nome, cpf, identificador);
                professor.setTitulos(livros);

                return professor;

            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }
}
