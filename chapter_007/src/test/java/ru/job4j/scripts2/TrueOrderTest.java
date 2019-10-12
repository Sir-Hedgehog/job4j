package ru.job4j.scripts2;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.10.2019
 */

public class TrueOrderTest {
    @Test
    public void checkDependenciesForDepthOf2() {
        Map<Integer, List<Integer>> unsorted = new HashMap<>(Map.of(3, List.of(4, 5), 5, List.of(), 4, List.of(), 2, List.of(4), 1, List.of(2, 3)));
        TrueOrder trueOrder = new TrueOrder();
        List<Integer> result = trueOrder.order(unsorted, 1);
        List<Integer> expect = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        assertThat(expect, is(result));
    }
}
