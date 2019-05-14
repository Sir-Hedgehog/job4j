package ru.job4j.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.05.2019
 */

public class Client {
    public void go() throws IOException {
        int port = 9000;
        String ip = "127.0.0.1";
        String word;
        Socket socket = new Socket(InetAddress.getByName(ip), port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        do {
            String str = in.readLine();
            word = console.nextLine();
            out.write(word + "\n");
            out.flush();
            while (!str.isEmpty()) {
                System.out.println(str);
            }
        } while (!word.equals("Выход"));
    }
}
