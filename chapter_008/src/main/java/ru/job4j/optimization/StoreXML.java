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
 * @since 14.07.2019
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

    public File save(List<Entry> values) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Entries entries = new Entries(values);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(entries, target);
        return target;
    }

    /*public static void main(String[] args) throws Exception {
        StoreXML store = new StoreXML();
        List<Entry> list = new ArrayList<>(Arrays.asList(new Entry(1), new Entry(2)));
        store.save(list);
    }*/
}
