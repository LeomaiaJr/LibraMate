package br.inatel.libramate.Util;

import java.util.Scanner;

import br.inatel.libramate.Exception.CpfDuplicadoException;
import br.inatel.libramate.Exception.EmprestarQuantidadeMaxException;
import br.inatel.libramate.Exception.LivroDuplicadoException;
import br.inatel.libramate.InputController.LivroInputController;
import br.inatel.libramate.InputController.PessoaInputController;

public class Menu {
    static public void startMenu() {
        Scanner scanner = ScannerSingleton.getInstance();

        boolean flag = true;

        while (flag) {
            System.out.println("===== Menu =====");
            System.out.println("1) Adicionar Pessoa");
            System.out.println("2) Ver Todas Pessoas");
            System.out.println("3) Adicionar Livro");
            System.out.println("4) Ver Livros");
            System.out.println("5) Emprestar Livro");
            System.out.println("6) Devolver Livro");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    addPessoa();
                    break;
                case 2:
                    verTodasPessoas();
                    break;
                case 3:
                    addLivro();
                    break;
                case 4:
                    verLivros();
                    break;
                case 5:
                    emprestarLivro();
                    break;
                case 6:
                    devolverLivro();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }

        ScannerSingleton.close();
    }

    static private void addPessoa() {
        PessoaInputController pessoaInputController = new PessoaInputController();

        try {
            pessoaInputController.save();
            System.out.println("Pessoa adicionada com sucesso!");
        } catch (CpfDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    static private void verTodasPessoas() {
        PessoaInputController pessoaInputController = new PessoaInputController();
        pessoaInputController.showAll();
    }

    static private void addLivro() {
        LivroInputController livroInputController = new LivroInputController();

        try {
            livroInputController.save();
            System.out.println("Livro adicionado com sucesso!");
        } catch (LivroDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    static private void verLivros() {
        LivroInputController livroInputController = new LivroInputController();
        livroInputController.showAll();
    }

    static private void emprestarLivro() {
        PessoaInputController pessoaInputController = new PessoaInputController();

        try {
            pessoaInputController.emprestarLivro();
            System.out.println("Livro emprestado com sucesso!");
        } catch (EmprestarQuantidadeMaxException e) {
            System.out.println(e.getMessage());
        }

    }

    static private void devolverLivro() {
        PessoaInputController pessoaInputController = new PessoaInputController();
        pessoaInputController.devolverLivro();
        System.out.println("Livro devolvido com sucesso!");
    }

}
