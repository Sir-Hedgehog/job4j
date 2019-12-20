package ru.job4j.clone;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class Motherboard implements Cloneable {
    private String manufacturer;
    private String chipset;
    private boolean overclocking;
    private boolean radiators;

    public Motherboard(String manufacturer, String chipset, boolean overclocking, boolean radiators) {
        this.manufacturer = manufacturer;
        this.chipset = chipset;
        this.overclocking = overclocking;
        this.radiators = radiators;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public void setOverclocking(boolean overclocking) {
        this.overclocking = overclocking;
    }

    public void setRadiators(boolean radiators) {
        this.radiators = radiators;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getChipset() {
        return chipset;
    }

    public boolean isOverclocking() {
        return overclocking;
    }

    public boolean isRadiators() {
        return radiators;
    }

    @Override
    public Motherboard clone() {
        Motherboard result = null;
        try {
            result = (Motherboard) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
