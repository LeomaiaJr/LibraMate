package br.inatel.libramate.InputController;

import java.util.ArrayList;
import java.util.Scanner;

import br.inatel.libramate.Exception.CpfDuplicadoException;
import br.inatel.libramate.Exception.EmprestarQuantidadeMaxException;
import br.inatel.libramate.FileController.LivroFileController;
import br.inatel.libramate.FileController.PessoaFileController;
import br.inatel.libramate.Model.Aluno;
import br.inatel.libramate.Model.Livro;
import br.inatel.libramate.Model.Pessoa;
import br.inatel.libramate.Model.Professor;
import br.inatel.libramate.Util.ScannerSingleton;

public class PessoaInputController extends GenericInputController<Pessoa> {

    @Override
    public Pessoa getTerminalInput() {
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        ArrayList<String> options = new ArrayList<String>();
        options.add("Aluno");
        options.add("Professor");

        System.out.println("Escolha o tipo de pessoa: ");
        String tipo = getTerminalOptionInput(options);

        if (tipo.equals("Aluno")) {
            int numeroMatricula = getTerminalNumberInput("Digite o numero de matricula: ");

            return new Aluno(nome, cpf, numeroMatricula);

        } else {
            int numeroRegistro = getTerminalNumberInput("Digite o numero de registro: ");

            return new Professor(nome, cpf, numeroRegistro);
        }
    }

    @Override
    public void save() throws CpfDuplicadoException {
        Pessoa pessoa = getTerminalInput();

        PessoaFileController pessoaFileController = new PessoaFileController();
        ArrayList<Pessoa> pessoas = pessoaFileController.read();

        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(pessoa.getCpf())) {
                throw new CpfDuplicadoException(pessoa.getCpf());
            }
        }

        pessoaFileController.add(pessoa);
    }

    public String getTerminalCpfInput(String prompt, boolean filter) {
        PessoaFileController pessoaFileController = new PessoaFileController();
        ArrayList<Pessoa> pessoas = pessoaFileController.read();

        ArrayList<String> cpfs = new ArrayList<String>();
        for (Pessoa pessoa : pessoas) {
            if (filter && pessoa.getTitulos().size() == 0) {
                continue;
            }
            cpfs.add(pessoa.getCpf());
        }

        System.out.println(prompt);
        String cpf = getTerminalOptionInput(cpfs);

        return cpf;
    }

    private Pessoa preloadPessoa(Pessoa pessoa) {
        LivroFileController livroFileController = new LivroFileController();
        ArrayList<Livro> livros = livroFileController.read();

        ArrayList<Livro> livrosPessoa = new ArrayList<Livro>();
        for (Livro livro : livros) {
            if (pessoa.getTitulos().contains(livro.getTitulo())) {
                livrosPessoa.add(livro);
            }
        }

        pessoa.setLivros(livrosPessoa);

        return pessoa;
    }

    @Override
    public void showAll() {
        PessoaFileController pessoaFileController = new PessoaFileController();
        ArrayList<Pessoa> pessoas = pessoaFileController.read();

        if (pessoas.size() == 0) {
            System.out.println("Não há pessoas cadastradas.");
            return;
        }

        for (Pessoa pessoa : pessoas) {
            pessoa = preloadPessoa(pessoa);
            pessoa.showInfo();
        }
    }

    public void emprestarLivro() throws EmprestarQuantidadeMaxException {
        LivroFileController livroFileController = new LivroFileController();
        LivroInputController livroInputController = new LivroInputController();
        PessoaFileController pessoaFileController = new PessoaFileController();

        ArrayList<Pessoa> pessoas = pessoaFileController.read();

        String cpf = getTerminalCpfInput("Digite o CPF da pessoa que deseja pegar o livro emprestado: ", false);

        Pessoa pessoa = null;
        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(cpf)) {
                pessoa = preloadPessoa(p);
                break;
            }
        }

        boolean podeEmprestar = pessoa.getQuantidadeMaximaLivros() > pessoa.getLivros().size();

        if (!podeEmprestar)
            throw new EmprestarQuantidadeMaxException(pessoa.getNome());

        String titulo = livroInputController.getTerminalTituloInput("Digite o titulo do livro que deseja emprestar: ",
                true, true);

        ArrayList<Livro> livros = livroFileController.read();
        ArrayList<Livro> pessoaLivro = pessoa.getLivros();

        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                pessoaLivro.add(livro);
                livro.setEmprestado(true);
                break;
            }
        }
        livroFileController.writeAll(livros);

        for (Livro livro : pessoaLivro) {
            if (livro.getTitulo().equals(titulo)) {
                livro.setEmprestado(true);
                break;
            }
        }

        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(cpf)) {
                p.setLivros(pessoaLivro);
                break;
            }
        }

        pessoaFileController.writeAll(pessoas);

    }

    public void devolverLivro() {
        LivroFileController livroFileController = new LivroFileController();
        PessoaFileController pessoaFileController = new PessoaFileController();

        ArrayList<Pessoa> pessoas = pessoaFileController.read();

        String cpf = getTerminalCpfInput("Digite o CPF da pessoa que deseja devolver o livro: ", true);

        Pessoa pessoa = null;
        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(cpf)) {
                pessoa = preloadPessoa(p);
                break;
            }
        }

        ArrayList<String> titulos = new ArrayList<String>();
        for (Livro livro : pessoa.getLivros()) {
            titulos.add(livro.getTitulo());
        }
        String titulo = getTerminalOptionInput(titulos);

        ArrayList<Livro> livros = livroFileController.read();
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                livro.setEmprestado(false);
                break;
            }
        }
        livroFileController.writeAll(livros);

        ArrayList<Livro> pessoaLivro = new ArrayList<Livro>();
        for (Livro livro : pessoa.getLivros()) {
            if (!livro.getTitulo().equals(titulo)) {
                pessoaLivro.add(livro);
            }
        }

        for (Pessoa p : pessoas) {
            if (p.getCpf().equals(cpf)) {
                p.setLivros(pessoaLivro);
                break;
            }
        }

        pessoaFileController.writeAll(pessoas);
    }

}
