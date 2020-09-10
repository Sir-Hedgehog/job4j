package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;
import static java.util.Collections.emptyList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.09.2020
 */

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private PersonRepository users;

    @Autowired
    public UserDetailsServiceImplementation(PersonRepository users) {
        this.users = users;
    }

    /**
     * Метод загружает детали авторизованного пользователя в SecurityContextHolder
     * @param login - логин
     * @return - данные пользователя
     */

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person user = users.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
