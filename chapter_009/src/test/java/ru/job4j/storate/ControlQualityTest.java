package ru.job4j.storate;

import org.junit.Test;
import ru.job4j.storage.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.11.2019
 */

public class ControlQualityTest {
    @Test
    public void checkCheeseInShopWithoutDiscount() {
        LocalDate expireDate = LocalDate.of(2019, 11, 30);
        LocalDate createDate = LocalDate.of(2019, 11, 10);
        Cheese cheese = new Cheese("Костромской", expireDate, createDate, 400, 0.3);
        ControlQuality controlQuality = new ControlQuality(cheese);
        controlQuality.distribute();
        List<String> result = controlQuality.checkShopWithoutDiscount();
        assertThat(result, is(new ArrayList<>(List.of("Костромской"))));
    }

    @Test
    public void checkSausageInShopWithDiscount() {
        LocalDate expireDate = LocalDate.of(2019, 11, 26);
        LocalDate createDate = LocalDate.of(2019, 10, 28);
        Sausage sausage = new Sausage("Папа может!", expireDate, createDate, 600, 0.2);
        ControlQuality controlQuality = new ControlQuality(sausage);
        controlQuality.distribute();
        Map<String, Double> result = controlQuality.checkShopWithDiscount();
        assertThat(result, is(new HashMap<>(Map.of("Папа может!", 480.0))));
    }

    @Test
    public void checkMilkInTrash() {
        LocalDate expireDate = LocalDate.of(2019, 11, 21);
        LocalDate createDate = LocalDate.of(2019, 11, 14);
        Milk milk = new Milk("Простоквашино", expireDate, createDate, 70, 0.1);
        ControlQuality controlQuality = new ControlQuality(milk);
        controlQuality.distribute();
        List<String> result = controlQuality.checkTrash();
        assertThat(result, is(new ArrayList<>(List.of("Простоквашино"))));
    }

    @Test
    public void checkYogurtInWarehouse() {
        LocalDate expireDate = LocalDate.of(2019, 12, 4);
        LocalDate createDate = LocalDate.of(2019, 11, 20);
        Yogurt yogurt = new Yogurt("Слобода", expireDate, createDate, 30, 0.05);
        ControlQuality controlQuality = new ControlQuality(yogurt);
        controlQuality.distribute();
        List<String> result = controlQuality.checkWarehouse();
        assertThat(result, is(new ArrayList<>(List.of("Слобода"))));
    }
}
