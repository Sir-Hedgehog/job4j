package ru.job4j.department;

import org.junit.Test;

import java.util.List;
import java.util.TreeSet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTest {
    @Test
    public void whenNaturalOrderThen() {
        String[] elements = {"K1\\SK1\\SSK2", "K1\\SK1", "K2", "K2\\SK1\\SSK2", "K1\\SK1\\SSK1", "K1\\SK2", "K2\\SK1\\SSK1"};
        Department department = new Department();
        for (int index = 0; index != elements.length; index++) {
            department.add(elements[index]);
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
        assertThat(department.naturalOrder, is(expect));
    }

    /*@Test
    public void whenReverseOrderThen() {
        String[] elements = {"K1\\SK1\\SSK2", "K1\\SK1", "K2", "K2\\SK1\\SSK2", "K1\\SK1\\SSK1", "K1\\SK2", "K2\\SK1\\SSK1"};
        Department department = new Department();
        for (int index = 0; index != elements.length; index++) {
            department.add(elements[index]);
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
        assertThat(department.reversedOrder, is(expect));
    }*/
}
