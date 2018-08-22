package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateOfLanguages() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = {"Java", "Scala", "Python", "Java", "JavaScript", "Swift", "Swift"};
        String[] expect = {"Java", "JavaScript", "Python", "Scala", "Swift"};
        String[] result = duplicate.remove(input);
        assertThat(result, arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateOfTerms() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input = {"компиляция", "рефакторинг", "конкатенация", "инкапсуляция", "инкапсуляция", "наследование"};
        String[] expect = {"инкапсуляция", "компиляция", "конкатенация", "наследование", "рефакторинг"};
        String[] result = duplicate.remove(input);
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
}
