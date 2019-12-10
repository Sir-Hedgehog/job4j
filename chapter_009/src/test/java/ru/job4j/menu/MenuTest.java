package ru.job4j.menu;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 10.12.2019
 */

public class MenuTest {
    @Test
    public void whenAllTitlesAreCorrectThenGetSuccess() {
        HeadManager menuItem1 = new MenuItem("1. Жизнь замечательных мужчин");
        HeadManager menuItem2 = new MenuItem("1.1. Суворов Александр Васильевич");
        HeadManager menuItem3 = new MenuItem("1.1.1. Битва при Рымнике");
        HeadManager menuItem4 = new MenuItem("1.1.2. Поход через Альпы");
        HeadManager menuItem5 = new MenuItem("1.2. Ушаков Федор Васильевич");
        HeadManager menuItem6 = new MenuItem("1.2.1. Сражение у мыса Тендра");
        HeadManager menuItem7 = new MenuItem("2. Жизнь замечательных женщин");
        TreeMap<String, HeadManager> heads = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        TreeMap<String, HeadManager> expect = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        for (String head : heads.keySet()) {
            System.out.println(head + "\n");
        }
        assertThat(heads, is(expect));
    }

    @Test(expected = NullPointerException.class)
    public void whenAllTitlesAreIncorrectThenGetSuccess() {
        HeadManager menuItem1 = new MenuItem("1. Жизнь замечательных мужчин");
        HeadManager menuItem2 = new MenuItem("1.1. Суворов Александр Васильевич");
        HeadManager menuItem3 = new MenuItem("+++1.1.1. Битва при Рымнике");
        HeadManager menuItem4 = new MenuItem("абв1.1.2. Поход через Альпы");
        HeadManager menuItem5 = new MenuItem("/*1.2. Ушаков Федор Васильевич");
        HeadManager menuItem6 = new MenuItem("1.2.1. Сражение у мыса Тендра");
        HeadManager menuItem7 = new MenuItem("2. Жизнь замечательных женщин");
        TreeMap<String, HeadManager> heads = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        for (String title : heads.keySet()) {
            System.out.println(title + "\n");
        }
        TreeMap<String, HeadManager> expect = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        assertThat(heads, is(expect));
    }

    @Test
    public void whenUserAddNewTitlesThenGetSuccess() {
        HeadManager menuItem1 = new MenuItem("1. Жизнь замечательных мужчин");
        HeadManager menuItem2 = new MenuItem("1.1. Суворов Александр Васильевич");
        HeadManager menuItem3 = new MenuItem("1.1.1. Битва при Рымнике");
        HeadManager menuItem4 = new MenuItem("1.1.2. Поход через Альпы");
        HeadManager menuItem5 = new MenuItem("1.2. Ушаков Федор Васильевич");
        HeadManager menuItem6 = new MenuItem("1.2.1. Сражение у мыса Тендра");
        HeadManager menuItem7 = new MenuItem("2. Жизнь замечательных женщин");
        TreeMap<String, HeadManager> heads = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        Menu menu = new Menu(heads);
        HeadManager titleInTheMiddle = new MenuItem("1.1.3. Наследие Суворова");
        HeadManager titleInTheEnd = new MenuItem("2.1. Царица Тамара Грузинская");
        menu.add(titleInTheMiddle);
        menu.add(titleInTheEnd);
        TreeMap<String, HeadManager> expect = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7,
                titleInTheMiddle.order(), titleInTheMiddle,
                titleInTheEnd.order(), titleInTheEnd));
        for (String head : heads.keySet()) {
            System.out.println(head + "\n");
        }
        assertThat(heads, is(expect));
    }

    @Test
    public void whenUserDeleteTitlesThenGetSuccess() {
        HeadManager menuItem1 = new MenuItem("1. Жизнь замечательных мужчин");
        HeadManager menuItem2 = new MenuItem("1.1. Суворов Александр Васильевич");
        HeadManager menuItem3 = new MenuItem("1.1.1. Битва при Рымнике");
        HeadManager menuItem4 = new MenuItem("1.1.2. Поход через Альпы");
        HeadManager menuItem5 = new MenuItem("1.2. Ушаков Федор Васильевич");
        HeadManager menuItem6 = new MenuItem("1.2.1. Сражение у мыса Тендра");
        HeadManager menuItem7 = new MenuItem("2. Жизнь замечательных женщин");
        TreeMap<String, HeadManager> heads = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem4.order(), menuItem4,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6,
                menuItem7.order(), menuItem7));
        Menu menu = new Menu(heads);
        menu.delete(menuItem4);
        menu.delete(menuItem7);
        TreeMap<String, HeadManager> expect = new TreeMap<>(Map.of(menuItem1.order(), menuItem1,
                menuItem2.order(), menuItem2,
                menuItem3.order(), menuItem3,
                menuItem5.order(), menuItem5,
                menuItem6.order(), menuItem6));
        assertThat(heads, is(expect));
        for (String title : heads.keySet()) {
            System.out.println(title + "\n");
        }
    }
}
