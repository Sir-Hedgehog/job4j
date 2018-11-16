package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Петр", "Арсеньев", "534872", "Брянск")
        );
        List<Person> persons = phones.find("ев");
        assertThat(persons.iterator().next().getPhone(), is("534872"));
    }
}
