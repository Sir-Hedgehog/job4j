package ru.job4j.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.05.2019
 */

public class Server {
    private final Socket socket;
    public String[] array = {"Тебя ждет успех!",
            "Будет трудно, но это того стоит!",
            "Не оглядывайся назад!",
            "Не вздумай этого делать!",
            "Уверен, у тебя всё получится!",
            "Просто верь в это!",
            "Ты находишься на правильном пути!",
            "Не унывай!",
            "Тебе есть к чему стремиться!"};

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void go() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask;
        do {
            System.out.println("Ожидание команды...");
            ask = in.readLine();
            if ("Выход".equals(ask)) {
                out.println("До новых встреч!");
                break;
            }
            System.out.println(ask);
            if ("Привет".equals(ask)) {
                out.println("Здравствуй, мой друг, я Оракл!");
                out.println();
            } else {
                out.println(getAnswer());
                out.println();
            }
        } while (true);
    }

    private String getAnswer() {
        int random = (int) (Math.random() * array.length);
        return array[random];
    }
}
