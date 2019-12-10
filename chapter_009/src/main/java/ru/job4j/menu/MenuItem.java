package ru.job4j.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 10.12.2019
 */

public class MenuItem implements HeadManager {
    private String title;
    private Pattern pattern = Pattern.compile("^(((([1-9])\\.)+(\\s))(([А-Я]|[A-Z])(([а-яА-Я]|\\s)+|([a-zA-Z]|\\s)+)))$");

    public MenuItem(String title) {
        this.title = title;
    }

    /**
     * Метод проверяет заголовок на валидность
     * @return - валидный или нет
     */

    @Override
    public boolean accept() {
        Matcher matcher = pattern.matcher(this.title);
        return matcher.find();
    }

    /**
     * Метод возвращает заголовок, в случае если он валидный.
     * В противном случае возвращает null.
     * @return - заголовок или null
     */

    @Override
    public String order() {
        String result;
        if (this.accept()) {
            result = this.title;
        } else {
            result = null;
        }
        return result;
    }
}
