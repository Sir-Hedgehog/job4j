package ru.job4j.mailing;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 09.02.2020
 */

public class EmailNotificationTest {
    @Test
    public void whenSendDataThenGetTemplateOfMessage() {
        EmailNotification notification = new EmailNotification();
        User user = new User("Nemo", "abracadabra@message.com");
        notification.emailTo(user);
        User user1 = new User("Nemo1", "abracadabra1@message.com");
        notification.emailTo(user1);
        User user2 = new User("Nemo2", "abracadabra2@message.com");
        notification.emailTo(user2);
        User user3 = new User("Nemo3", "abracadabra3@message.com");
        notification.emailTo(user3);
        User user4 = new User("Nemo4", "abracadabra4@message.com");
        notification.emailTo(user4);
        User user5 = new User("Nemo5", "abracadabra5@message.com");
        notification.emailTo(user5);
        assertThat(notification.close(), is(true));
    }
}
