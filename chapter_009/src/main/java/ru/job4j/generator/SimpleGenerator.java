package ru.job4j.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 29.11.2019
 */

public class SimpleGenerator implements Template {
    private final Pattern name = Pattern.compile("^(([A-Z]|[А-Я])([a-z]+|[а-я]+))$");
    private final Pattern ipv4 = Pattern.compile("^(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))$");
    private final Pattern phoneNumber = Pattern.compile("^((8|\\+7)(-?)(9\\d\\d)(-?)(\\d{3})(-?)(\\d{2})(-?)(\\d{2}))$");

    /**
     * Метод по указанным ключам вставляет в текст соответствующие им слова
     * @param text - исходный текст
     * @param data - карта с ключами и соответствующими им словами
     * @return результирующий текст
     */
    @Override
    public String generate(String text, Map<String, String> data) throws NoKeysException, NotCorrectFormatException, ExtraKeysException {
        List<Matcher> matchers = new ArrayList<>();
        if (!data.containsKey("${name}")
                || !data.containsKey("${ipv4}")
                || !data.containsKey("${phoneNumber}")) {
            throw new NoKeysException("Such keys are not exist!");
        }
        for (Map.Entry<String, String> current : data.entrySet()) {
            Matcher matcher1 = name.matcher(current.getValue());
            Matcher matcher2 = ipv4.matcher(current.getValue());
            Matcher matcher3 = phoneNumber.matcher(current.getValue());
            if (current.getKey().contains("${name}") && matcher1.find()) {
                text = text.replace("${name}", current.getValue());
                matchers.add(matcher1);
            } else if (current.getKey().contains("${ipv4}") && matcher2.find()) {
                text = text.replace("${ipv4}", current.getValue());
                matchers.add(matcher2);
            } else if (current.getKey().contains("${phoneNumber}") && matcher3.find()) {
                text = text.replace("${phoneNumber}", current.getValue());
                matchers.add(matcher3);
            } else if (current.getKey().contains("${name}") && !matcher1.find()
                    || current.getKey().contains("${ipv4}") && !matcher2.find()
                    || current.getKey().contains("${phoneNumber}") && !matcher3.find()) {
                throw new NotCorrectFormatException("There is not correct format!");
            }
        }
        if (data.size() > matchers.size()) {
            throw new ExtraKeysException("There are extra keys!");
        }
        return text;
    }
}
