package ru.job4j.hotel;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 16.05.2019
 */

public class HotelsTest {
    @Test
    public void whenThereIsRatingsOfFiveHotelsThenShowStars() {
        Hotels hotels = new Hotels();
        Integer[] input = {13, 56, 97, 90, 4};
        int[] output = hotels.determine(5, input);
        int[] expect = {2, 3, 5, 4, 1};
        assertThat(output, is(expect));
    }

    @Test
    public void whenThereIsRatingsOfMoreThanFiveHotelsThenShowStars() {
        Hotels hotels = new Hotels();
        Integer[] input = {13, 56, 97, 90, 4, 68, 80, 69, 51, 7};
        int[] output = hotels.determine(10, input);
        int[] expect = {2, 3, 5, 5, 1, 3, 4, 4, 2, 1};
        assertThat(output, is(expect));
    }
}
