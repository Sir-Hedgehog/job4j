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
 * @version 4.0
 * @since 27.11.2019
 */

public class ControlQualityTest {
    @Test
    public void checkCheeseInShopWithoutDiscount() {
        LocalDate expireDateOfCheese = LocalDate.of(2019, 12, 2);
        LocalDate createDateOfCheese = LocalDate.of(2019, 11, 15);
        Cheese cheese = new Cheese("Костромской", expireDateOfCheese, createDateOfCheese, 400, 0.3);
        LocalDate expireDateOfMilk = LocalDate.of(2019, 12, 4);
        LocalDate createDateOfMilk = LocalDate.of(2019, 11, 26);
        Milk milk = new Milk("Parmalat", expireDateOfMilk, createDateOfMilk, 100, 0.15);
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribute(cheese);
        controlQuality.distribute(milk);
        assertThat(shop.getFood(), is(new ArrayList<>(List.of("Костромской"))));
    }

    @Test
    public void checkSausageInShopWithDiscount() {
        LocalDate expireDateOfSausage = LocalDate.of(2019, 11, 28);
        LocalDate createDateOfSausage = LocalDate.of(2019, 10, 30);
        Sausage sausage = new Sausage("Папа может!", expireDateOfSausage, createDateOfSausage, 600, 0.2);
        LocalDate expireDateOfCheese = LocalDate.of(2019, 12, 7);
        LocalDate createDateOfCheese = LocalDate.of(2019, 11, 17);
        Cheese cheese = new Cheese("Гауда", expireDateOfCheese, createDateOfCheese, 400, 0.3);
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribute(sausage);
        controlQuality.distribute(cheese);
        assertThat(shop.getDiscountFoods(), is(new HashMap<>(Map.of("Папа может!", 480.0))));
    }

    @Test
    public void checkMilkInTrash() {
        LocalDate expireDateOfMilk = LocalDate.of(2019, 11, 21);
        LocalDate createDateOfMilk = LocalDate.of(2019, 11, 14);
        Milk milk = new Milk("Простоквашино", expireDateOfMilk, createDateOfMilk, 70, 0.1);
        LocalDate expireDateOfSausage = LocalDate.of(2019, 11, 28);
        LocalDate createDateOfSausage = LocalDate.of(2019, 10, 30);
        Sausage sausage = new Sausage("Останкино", expireDateOfSausage, createDateOfSausage, 600, 0.2);
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribute(sausage);
        controlQuality.distribute(milk);
        assertThat(trash.getFood(), is(new ArrayList<>(List.of("Простоквашино"))));
    }

    @Test
    public void checkYogurtInWarehouse() {
        LocalDate expireDateOfYogurt = LocalDate.of(2019, 12, 8);
        LocalDate createDateOfYogurt = LocalDate.of(2019, 11, 25);
        Yogurt yogurt = new Yogurt("Слобода", expireDateOfYogurt, createDateOfYogurt, 30, 0.05);
        LocalDate expireDateOfMilk = LocalDate.of(2019, 11, 21);
        LocalDate createDateOfMilk = LocalDate.of(2019, 11, 14);
        Milk milk = new Milk("Домик в деревне", expireDateOfMilk, createDateOfMilk, 70, 0.1);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribute(yogurt);
        controlQuality.distribute(milk);
        assertThat(warehouse.getFood(), is(new ArrayList<>(List.of("Слобода"))));
    }

    @Test
    public void checkResort() {
        LocalDate expireDateOfYogurt = LocalDate.of(2019, 12, 8);
        LocalDate createDateOfYogurt = LocalDate.of(2019, 11, 25);
        Yogurt yogurt = new Yogurt("Слобода", expireDateOfYogurt, createDateOfYogurt, 30, 0.05);
        LocalDate expireDateOfMilk = LocalDate.of(2019, 11, 30);
        LocalDate createDateOfMilk = LocalDate.of(2019, 11, 21);
        Milk milk = new Milk("Домик в деревне", expireDateOfMilk, createDateOfMilk, 70, 0.1);
        LocalDate expireDateOfSausage = LocalDate.of(2019, 11, 29);
        LocalDate createDateOfSausage = LocalDate.of(2019, 11, 10);
        Sausage sausage = new Sausage("Коломенская", expireDateOfSausage, createDateOfSausage, 180, 0.15);
        LocalDate expireDateOfCheese = LocalDate.of(2019, 11, 25);
        LocalDate createDateOfCheese = LocalDate.of(2019, 11, 1);
        Cheese cheese = new Cheese("Король Артур", expireDateOfCheese, createDateOfCheese, 400, 0.25);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribute(yogurt);
        controlQuality.distribute(milk);
        controlQuality.distribute(cheese);
        controlQuality.distribute(sausage);
        List<Food> list = new ArrayList<>(List.of(yogurt, milk, cheese, sausage));
        controlQuality.resort(list);
        assertThat(warehouse.getFood(), is(new ArrayList<>(List.of("Слобода"))));
        assertThat(trash.getFood(), is(new ArrayList<>(List.of("Король Артур"))));
        assertThat(shop.getFood(), is(new ArrayList<>(List.of("Домик в деревне"))));
        assertThat(shop.getDiscountFoods(), is(new HashMap<>(Map.of("Коломенская", 153.0))));
    }
}
