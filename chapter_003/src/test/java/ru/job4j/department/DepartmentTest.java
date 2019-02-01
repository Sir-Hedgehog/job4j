package ru.job4j.department;

import org.junit.Test;
import java.util.TreeSet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTest {
    @Test
    public void whenNaturalOrderThen() {
        TreeSet<String> naturalOrder = new TreeSet<>();
        naturalOrder.add("K1");
        naturalOrder.add("K1\\SK1\\SSK2");
        naturalOrder.add("K2\\SK1");
        naturalOrder.add("K1\\SK1");
        naturalOrder.add("K2");
        naturalOrder.add("K2\\SK1\\SSK2");
        naturalOrder.add("K1\\SK1\\SSK1");
        naturalOrder.add("K1\\SK2");
        naturalOrder.add("K2\\SK1\\SSK1");
        Department department = new Department();
        for (String string : naturalOrder) {
            department.add(string);
        }
        TreeSet<String> expect = new TreeSet<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");
        assertThat(naturalOrder, is(expect));
    }

    @Test
    public void whenReverseOrderThen() {
        TreeSet<String> reverseOrder = new TreeSet<>();
        reverseOrder.add("K1");
        reverseOrder.add("K1\\SK1\\SSK2");
        reverseOrder.add("K2\\SK1");
        reverseOrder.add("K1\\SK1");
        reverseOrder.add("K2");
        reverseOrder.add("K2\\SK1\\SSK2");
        reverseOrder.add("K1\\SK1\\SSK1");
        reverseOrder.add("K1\\SK2");
        reverseOrder.add("K2\\SK1\\SSK1");
        Department department = new Department();
        for (String string : reverseOrder) {
            department.add(string);
        }
        TreeSet<String> expect = new TreeSet<>();
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K1");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");
        assertThat(reverseOrder, is(expect));
    }
}
