package ru.job4j.optimization;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 28.07.2019
 */

public class OptimizationTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private StoreXML.Entries help(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(StoreXML.Entries.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (StoreXML.Entries) unmarshaller.unmarshal(file);
    }

    private boolean checkResult(long totalTime) {
        boolean result = false;
        if (totalTime < 300000) {
            result = true;
        }
        return result;
    }

    //@Ignore
    @Test
    public void whenTestStoreSQLThenOK() {
        Config configuration = new Config();
        configuration.init();
        List<Entry> list;
        List<Entry> expected = new ArrayList<>();
        expected.add(new Entry(1));
        expected.add(new Entry(2));
        StoreSQL store = new StoreSQL(configuration);
        store.generate(2);
        list = store.load();
        assertThat(list, is(expected));
    }

    @Test
    public void whenTestStoreXMLThenOK() throws Exception {
        File file = folder.newFile();
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(1));
        list.add(new Entry(2));
        StoreXML store = new StoreXML(file);
        store.save(list);
        StoreXML.Entries total = this.help(file);
        assertThat(list, is(total.getValues()));
    }

    @Test
    public void whenTestConvertXSLTThenOK() throws Exception {
        File source = folder.newFile();
        File scheme = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("convertation.xsl")).getFile());
        File dest = folder.newFile();
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(1));
        list.add(new Entry(2));
        StoreXML store = new StoreXML(source);
        store.save(list);
        ConvertXSLT xslt = new ConvertXSLT();
        xslt.convert(source, dest, scheme);
        SAXChanger sax = new SAXChanger(dest);
        Long result = sax.parseNumber();
        assertThat(result, is(3L));
    }

    @Ignore
    @Test
    public void whenTestStoresSpeedThenOK() throws Exception {
        long startTime = System.currentTimeMillis();
        //Config -> StoreSQL
        Config config = new Config();
        config.init();
        List<Entry> entries;
        StoreSQL sql = new StoreSQL(config);
        sql.generate(1000000);
        entries = sql.load();
        //StoreSQL -> StoreXML
        File source = folder.newFile();
        StoreXML xml = new StoreXML(source);
        xml.save(entries);
        //StoreXML -> ConvertXSLT -> SAXChanger
        File dest = folder.newFile();
        File scheme = new File(getClass().getClassLoader().getResource("convertation.xsl").getFile());
        ConvertXSLT xslt = new ConvertXSLT();
        xslt.convert(source, dest, scheme);
        SAXChanger sax = new SAXChanger(dest);
        Long result = sax.parseNumber();
        assertThat(result, is(500000500000L));
        long stopTime = System.currentTimeMillis();
        long totalTime = stopTime - startTime;
        assertThat(checkResult(totalTime), is (true));
        sql.dropTable();
    }
}
