package ru.job4j.optimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 27.07.2019
 */

public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public StoreXML() {
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entries {

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

    public File save(List<Entry> entry) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Entries entries = new Entries(entry);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(entries, target);
        return target;
    }
}
