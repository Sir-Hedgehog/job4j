package ru.job4j.tracker;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 18.09.2018
 */

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
