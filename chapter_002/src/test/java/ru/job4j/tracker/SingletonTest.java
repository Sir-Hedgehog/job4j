package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.singleton.ClassSingleton;
import ru.job4j.tracker.singleton.EnumSingleton;
import ru.job4j.tracker.singleton.StaticFieldSingleton;
import ru.job4j.tracker.singleton.StaticFinalFieldSingleton;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public class SingletonTest {
    @Test
    public void whenEnumSingleton() {
        EnumSingleton first = EnumSingleton.SINGLETON;
        EnumSingleton.SINGLETON.add(new Item("test1", "description1"));
        EnumSingleton second = EnumSingleton.SINGLETON;
        assertThat(first == second, is(true));
    }

    @Test
    public void whenStaticFieldSingleton() {
        StaticFieldSingleton first = StaticFieldSingleton.getInstance();
        StaticFieldSingleton.getInstance().add(new Item("test1", "description1"));
        StaticFieldSingleton second = StaticFieldSingleton.getInstance();
        assertThat(first == second, is(true));
    }

    @Test
    public void whenStaticFinalFieldSingleton() {
        StaticFinalFieldSingleton first = StaticFinalFieldSingleton.getInstance();
        StaticFinalFieldSingleton.getInstance().add(new Item("test1", "description1"));
        StaticFinalFieldSingleton second = StaticFinalFieldSingleton.getInstance();
        assertThat(first == second, is(true));
    }

    @Test
    public void whenClassSingleton() {
        ClassSingleton first = ClassSingleton.getInstance();
        ClassSingleton.getInstance().add(new Item("test1", "description1"));
        ClassSingleton second = ClassSingleton.getInstance();
        assertThat(first == second, is(true));
    }
}
