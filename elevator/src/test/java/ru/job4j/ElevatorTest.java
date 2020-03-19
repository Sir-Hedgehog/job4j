package ru.job4j;

import org.junit.Test;
import java.util.concurrent.ExecutionException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 19.03.2020
 */

public class ElevatorTest {
    @Test
    public void whenElevatorWorksThenGetResultOfWorking() throws ExecutionException, InterruptedException {
        Elevator elevator = new Elevator(10, 6);
        for (int index = 0; index < 10; index++) {
            elevator.move();
        }
        assertThat(elevator.closePool(), is(true));
    }
}
