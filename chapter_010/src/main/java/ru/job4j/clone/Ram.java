package ru.job4j.clone;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.12.2019
 */

public class Ram implements Cloneable {
    private String manufacturer;
    private int frequency;
    private String type;
    private boolean radiators;

    public Ram(String manufacturer, int frequency, String type, boolean radiators) {
        this.manufacturer = manufacturer;
        this.frequency = frequency;
        this.type = type;
        this.radiators = radiators;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadiators(boolean radiators) {
        this.radiators = radiators;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getFormFactor() {
        return type;
    }

    public boolean isRadiators() {
        return radiators;
    }

    @Override
    public Ram clone() {
        Ram result = null;
        try {
            result = (Ram) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
