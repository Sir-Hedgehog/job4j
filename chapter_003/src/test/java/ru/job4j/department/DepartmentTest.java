package ru.job4j.department;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTest {
    @Test
    public void whenNaturalOrderThen() {
        String[] array = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1"};
        Department department = new Department();
        List<String> list = department.toList(array);
        List<String> result = department.sortNaturalOrder(list);
        List<String> expect = new ArrayList<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");
        assertThat(result, is(expect));
    }

    @Test
    public void whenReverseOrderThen() {
        String[] array = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1"};
        Department department = new Department();
        List<String> list = department.toList(array);
        List<String> result = department.sortReverseOrder(list);
        List<String> expect = new ArrayList<>();
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1");
        expect.add("K2");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1");
        expect.add("K1");
        assertThat(result, is(expect));
    }
}
