package ru.job4j.optimization;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 27.07.2019
 */

public class SAXChanger {
    private File file;

    public SAXChanger(File file) {
        this.file = file;
    }

    public class XMLHandler extends DefaultHandler {
        private Long sum = 0L;

        @Override
        public void startElement(String uri, String localName, String entry, Attributes attributes) {
            try {
                if (entry.equals("entry")) {
                    sum += Long.parseLong(attributes.getValue("number"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Long getSum() {
            return sum;
        }
    }

    public Long parseNumber() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(file, handler);
        return handler.getSum();
    }
}
