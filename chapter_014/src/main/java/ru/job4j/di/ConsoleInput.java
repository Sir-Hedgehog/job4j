package ru.job4j.di;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.05.2020
 */

@Component
public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод задает вопрос пользователю с целью получения обратной связи
     * @param question - вопрос
     * @return - ответ
     */

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
