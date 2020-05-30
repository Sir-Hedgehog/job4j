package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.05.2020
 */

public class Context {
    private Map<String, Object> elements = new HashMap<>();

    /**
     * Метод регистрирует классы (основной класс и его зависимости)
     * @param cl - класс
     */

    public void register(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class arg : con.getParameterTypes()) {
            if (!elements.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(elements.get(arg.getCanonicalName()));
        }
        try {
            elements.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Couldn't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    /**
     * Метод возвращает проинициализированный объект
     * @param instance - класс
     * @param <T> - тип объекта класса
     * @return - объект класса
     */

    public <T> T get(Class<T> instance) {
        return (T) elements.get(instance.getCanonicalName());
    }
}