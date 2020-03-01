package ru.job4j.crud;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 01.03.2020
 */

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String photoId;
    private String createDate;

    public User(String name, String login, String email, String photoId) {
        this.id = ThreadLocalRandom.current().nextInt(1, 1000000);
        this.name = name;
        this.login = login;
        this.email = email;
        this.photoId = photoId.substring(photoId.lastIndexOf("\\") + 1);
        Date date = new Date();
        this.createDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
