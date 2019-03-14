package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.03.2019
 */

public class AnalysisTest {
    @Test
    public void whenThereIsListOfUsersThenCheckChangesAfterTransformation() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        previous.add(new Analysis.User(123, "Витя"));
        previous.add(new Analysis.User(456, "Петя"));
        previous.add(new Analysis.User(789, "Фетя"));
        List<Analysis.User> current = new ArrayList<>();
        current.add(new Analysis.User(123, "Витя"));
        current.add(new Analysis.User(654, "Вася"));
        current.add(new Analysis.User(456, "Маня"));
        Analysis.Info result = analysis.differ(previous, current);
        Analysis.Info expected = new Analysis.Info(1, 1, 1);
        assertThat(result, is(expected));
    }
}
