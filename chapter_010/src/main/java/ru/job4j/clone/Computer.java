package ru.job4j.clone;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class Computer implements Cloneable {
    private Cpu cpu;
    private Ram ram;
    private Motherboard motherboard;

    public Computer(Cpu cpu, Ram ram, Motherboard motherboard) {
        this.cpu = cpu;
        this.ram = ram;
        this.motherboard = motherboard;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    @Override
    public Computer clone() {
        Computer result = null;
        try {
            result = (Computer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
