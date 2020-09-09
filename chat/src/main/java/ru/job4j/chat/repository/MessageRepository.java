package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.chat.model.Message;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.09.2020
 */

@Component
public interface MessageRepository extends CrudRepository<Message, Integer> {

    /**
     * Метод возвращает список сообщений
     * @return - список сообщений
     */

    List<Message> findAll();
}
