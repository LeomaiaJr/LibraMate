package br.inatel.libramate.InputController;

import java.util.ArrayList;
import java.util.Scanner;

import br.inatel.libramate.Util.ScannerSingleton;

public abstract class GenericInputController<T> {

    public abstract T getTerminalInput();

    public String getTerminalOptionInput(ArrayList<String> options) {
        Scanner scanner = ScannerSingleton.getInstance();
        String input = "";

        while (true) {
            System.out.println("Escolha uma opção: " + options.toString());
            input = scanner.nextLine();

            if (options.contains(input)) {
                break;
            } else {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }

        return input;
    }

    public int getTerminalNumberInput(String prompt) {
        Scanner scanner = ScannerSingleton.getInstance();
        int input = 0;

        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Número inválido. Por favor, tente novamente.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        return input;
    }

    public abstract void save() throws Exception;

    public abstract void showAll();

}
