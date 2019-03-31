package ru.job4j.hotel;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HotelsTest {
    @Test
    @Ignore
    public void whenThereIsRatingOfHotelsThenStar() {
        Hotels hotels = new Hotels();
        Rating[] rating = {new Rating(10),
                new Rating(45),
                new Rating(13),
                new Rating(67),
                new Rating(90)};
        int[] result = hotels.determineStars(5, rating);
        int[] expect = {1, 3, 2, 4, 5};
        assertThat(result, is(expect));
    }
}
