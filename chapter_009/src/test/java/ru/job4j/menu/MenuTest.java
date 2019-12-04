package ru.job4j.menu;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 4.12.2019
 */

public class MenuTest {
    @Test
    public void whenAllTitlesAreCorrectThenGetSuccess() {
        HeadManager title1 = new FirstLevel("1. Жизнь замечательных мужчин");
        HeadManager title2 = new SecondLevel("---- 1.1. Суворов Александр Васильевич");
        HeadManager title3 = new ThirdLevel("-------- 1.1.1. Битва при Рымнике");
        HeadManager title4 = new ThirdLevel("-------- 1.1.2. Поход через Альпы");
        HeadManager title5 = new SecondLevel("---- 1.2. Ушаков Федор Васильевич");
        HeadManager title6 = new ThirdLevel("-------- 1.2.1. Сражение у мыса Тендра");
        HeadManager title7 = new FirstLevel("2. Жизнь замечательных женщин");
        List<String> heads = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        for (String title : heads) {
            System.out.println(title + "\n");
        }
        List<String> expect = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        assertThat(heads, is(expect));
    }

    @Test(expected = NullPointerException.class)
    public void whenAllTitlesAreIncorrectThenGetSuccess() {
        HeadManager title1 = new FirstLevel("1. Жизнь замечательных мужчин");
        HeadManager title2 = new FirstLevel("---- 1.1. Суворов Александр Васильевич");
        HeadManager title3 = new ThirdLevel("---+++++--- 1.1-1. Битва при Рымнике");
        HeadManager title4 = new ThirdLevel(" 1.1.2. Поход через Альпы");
        HeadManager title5 = new FirstLevel("---- 1.2. Ушаков Федор Васильевич");
        HeadManager title6 = new ThirdLevel("-------- 1.2.1. Сражение у мыса Тендра");
        HeadManager title7 = new FirstLevel("2. Жизнь замечательных женщин");
        List<String> heads = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        for (String title : heads) {
            System.out.println(title + "\n");
        }
        List<String> expect = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        assertThat(heads, is(expect));
    }

    @Test
    public void whenUserAddNewTitlesThenGetSuccess() {
        HeadManager title1 = new FirstLevel("1. Жизнь замечательных мужчин");
        HeadManager title2 = new SecondLevel("---- 1.1. Суворов Александр Васильевич");
        HeadManager title3 = new ThirdLevel("-------- 1.1.1. Битва при Рымнике");
        HeadManager title4 = new ThirdLevel("-------- 1.1.2. Поход через Альпы");
        HeadManager title5 = new SecondLevel("---- 1.2. Ушаков Федор Васильевич");
        HeadManager title6 = new ThirdLevel("-------- 1.2.1. Сражение у мыса Тендра");
        HeadManager title7 = new FirstLevel("2. Жизнь замечательных женщин");
        List<String> heads = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        Menu menu = new Menu(heads);
        HeadManager titleInTheMiddle = new ThirdLevel("-------- 1.1.3. Наследие Суворова");
        HeadManager titleInTheEnd = new SecondLevel("---- 2.1. Царица Тамара Грузинская");
        menu.add(title4, titleInTheMiddle);
        menu.add(title7, titleInTheEnd);
        List<String> expect = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                titleInTheMiddle.order(),
                title5.order(),
                title6.order(),
                title7.order(),
                titleInTheEnd.order()));
        assertThat(heads, is(expect));
        for (String title : heads) {
            System.out.println(title + "\n");
        }
    }

    @Test
    public void whenUserDeleteTitlesThenGetSuccess() {
        HeadManager title1 = new FirstLevel("1. Жизнь замечательных мужчин");
        HeadManager title2 = new SecondLevel("---- 1.1. Суворов Александр Васильевич");
        HeadManager title3 = new ThirdLevel("-------- 1.1.1. Битва при Рымнике");
        HeadManager title4 = new ThirdLevel("-------- 1.1.2. Поход через Альпы");
        HeadManager title5 = new SecondLevel("---- 1.2. Ушаков Федор Васильевич");
        HeadManager title6 = new ThirdLevel("-------- 1.2.1. Сражение у мыса Тендра");
        HeadManager title7 = new FirstLevel("2. Жизнь замечательных женщин");
        List<String> heads = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title4.order(),
                title5.order(),
                title6.order(),
                title7.order()));
        Menu menu = new Menu(heads);
        menu.delete(title4);
        menu.delete(title7);
        List<String> expect = new ArrayList<>(List.of(
                title1.order(),
                title2.order(),
                title3.order(),
                title5.order(),
                title6.order()));
        assertThat(heads, is(expect));
        for (String title : heads) {
            System.out.println(title + "\n");
        }
    }
}
