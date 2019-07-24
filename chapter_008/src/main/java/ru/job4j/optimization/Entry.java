package ru.job4j.optimization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.07.2019
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    private Integer number;

    public Entry(int number) {
        this.number = number;
    }

    public Entry() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return Objects.equals(number, entry.number);
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
