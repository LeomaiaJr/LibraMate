package br.inatel.libramate.InputController;

import java.util.ArrayList;
import java.util.Scanner;

import br.inatel.libramate.Exception.LivroDuplicadoException;
import br.inatel.libramate.FileController.LivroFileController;
import br.inatel.libramate.Model.Livro;
import br.inatel.libramate.Util.ScannerSingleton;

public class LivroInputController extends GenericInputController<Livro> {

    @Override
    public Livro getTerminalInput() {
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.println("Digite o titulo do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.println("Digite o genero literario do livro: ");
        String generoLiterario = scanner.nextLine();

        int qtdFolhas = getTerminalNumberInput("Digite a quantidade de folhas do livro: ");

        System.out.println("Digite a editora do livro: ");
        String editora = scanner.nextLine();

        int anoLancamento = getTerminalNumberInput("Digite o ano de lancamento do livro: ");

        return new Livro(titulo, autor, generoLiterario, qtdFolhas, editora, anoLancamento, false);
    }

    @Override
    public void save() throws LivroDuplicadoException {
        Livro livro = getTerminalInput();

        LivroFileController livroFileController = new LivroFileController();
        ArrayList<Livro> livros = livroFileController.read();

        for (Livro l : livros) {
            if (l.getTitulo().equals(livro.getTitulo())) {
                throw new LivroDuplicadoException(livro.getTitulo());
            }
        }

        livroFileController.add(livro);
    }

    public String getTerminalTituloInput(String prompt, boolean userFilter, boolean filter) {
        LivroFileController livroFileController = new LivroFileController();
        ArrayList<Livro> livros = livroFileController.read();

        ArrayList<String> titulos = new ArrayList<String>();
        for (Livro livro : livros) {
            if (userFilter && livro.getEmprestado() == filter) {
                continue;
            }
            titulos.add(livro.getTitulo());
        }

        System.out.println(prompt);
        String titulo = getTerminalOptionInput(titulos);

        return titulo;
    }

    @Override
    public void showAll() {
        LivroFileController livroFileController = new LivroFileController();
        ArrayList<Livro> livros = livroFileController.read();

        for (Livro livro : livros) {
            livro.showInfo();
        }
    }

    public void acaoLivro(Livro livro, boolean emprestar) {
        LivroFileController livroFileController = new LivroFileController();
        ArrayList<Livro> livros = livroFileController.read();

        for (Livro l : livros) {
            if (l.getTitulo().equals(livro.getTitulo())) {
                l.setEmprestado(emprestar);
                break;
            }
        }

        livroFileController.writeAll(livros);
    }

}
