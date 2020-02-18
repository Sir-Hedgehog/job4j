package ru.job4j.crud;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.02.2020
 */

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User(String name, String login, String email) {
        this.id = ThreadLocalRandom.current().nextInt(1, 1000000);
        this.name = name;
        this.login = login;
        this.email = email;
        Date date = new Date();
        this.createDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }


    @Override
    public String toString() {
        return "User: "
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate + "\n";
    }
}
