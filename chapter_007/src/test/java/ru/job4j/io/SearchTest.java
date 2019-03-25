package ru.job4j.io;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 25.03.2019
 */

public class SearchTest {
    @Test
    public void whenSearchNecessaryExpansionsThenShowsResult() {
        Search search = new Search();
        List<String> list = new ArrayList<>();
        list.add("txt");
        list.add("pdf");
        search.list("C:\\Windows\\Temp", list);

    }
}
