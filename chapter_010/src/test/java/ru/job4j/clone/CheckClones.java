package ru.job4j.clone;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class CheckClones {
    @Test
    public void checkSurfaceCloning() {
        Client client = new Client("Иван", 22, 'м');
        Client clone = client.clone();
        clone.setAge(70);
        assertThat(22, is(client.getAge()));
        assertThat(70, is(clone.getAge()));
        assertThat(clone.getName(), is(client.getName()));
        assertThat(clone.getSex(), is(client.getSex()));
    }

    @Test
    public void checkDeepCloning() {
        Cpu cpu = new Cpu("AMD", 8, 16,  3900, true);
        Ram ram = new Ram("Micron", 2400, "DDR4", false);
        Motherboard motherboard = new Motherboard("Asus", "Intel", true, true);
        Computer computer = new Computer(cpu, ram, motherboard);
        Computer clone = computer.clone();
        Ram newRam = new Ram("Corsair", 3200, "DDR4", true);
        clone.setRam(newRam);
        assertThat(computer.getCpu(), is(clone.getCpu()));
        assertThat(newRam, is(clone.getRam()));
        assertThat(computer.getMotherboard(), is(clone.getMotherboard()));
    }
}
