package ru.job4j.clone;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class Cpu implements Cloneable {
    private String manufacturer;
    private int cores;
    private int threads;
    private int frequency;
    private boolean overclocking;

    public Cpu(String manufacturer, int cores, int threads, int frequency, boolean overclocking) {
        this.manufacturer = manufacturer;
        this.cores = cores;
        this.threads = threads;
        this.frequency = frequency;
        this.overclocking = overclocking;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setOverclocking(boolean overclocking) {
        this.overclocking = overclocking;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getCores() {
        return cores;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isOverclocking() {
        return overclocking;
    }

    @Override
    public Cpu clone() {
        Cpu result = null;
        try {
            result = (Cpu) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
