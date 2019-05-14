package ru.job4j.sockets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.05.2019
 */

public class ServerTest {
    private static final String LN = System.getProperty("line.separator");

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.go();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskAnswerThenChooseRandom() throws IOException {
        this.testServer("Выход", "До новых встреч!\r\n");
    }

    @Test
    public void whenAskHelloThenWelcomeFromOracle() throws IOException {
        this.testServer(Joiner.on(LN).join("Привет", "Выход"), "Здравствуй, мой друг, я Оракл!\r\n\r\nДо новых встреч!\r\n");
    }
}
