package ru.job4j.optimization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

    @XmlElement(name = "entry")
    private List<Entry> values;

    public Entries() {
    }

    public Entries(List<Entry> values) {
        this.values = values;
    }

    public List<Entry> getValues() {
        return values;
    }

    public void setValues(List<Entry> values) {
        this.values = values;
    }
}