package ru.job4j.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 4.12.2019
 */

public class ThirdLevel implements HeadManager {
    private String title;
    private Pattern pattern = Pattern.compile("^((-){8}(\\s)((([1-9])\\.){3}(\\s))(([А-Я]|[A-Z])([а-яА-Я]|\\s)+|([a-zA-Z]+)))$");

    public ThirdLevel(String title) {
        this.title = title;
    }

    /**
     * Метод проверяет, правильно ли написан заголовок
     * @return - да или нет
     */

    public boolean accept() {
        Matcher matcher = pattern.matcher(this.title);
        return matcher.find();
    }

    /**
     * Метод возвращает заголовок в случае прохождения проверки на валидность
     * @return - заголовок или null
     */

    @Override
    public String order() {
        String result = "";
        if (this.accept()) {
            result = this.title;
        } else {
            result = null;
        }
        return result;
    }
}
