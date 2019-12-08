package ru.job4j.generator;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 8.12.2019
 */

public class TemplateTest {
    @Test
    public void whenInputCorrectDataThenOutputTemplateText() {
        Name name = new Name();
        PhoneNumber phoneNumber = new PhoneNumber();
        Ipv4 ipv4 = new Ipv4();
        Template template = new SimpleGenerator(name, phoneNumber, ipv4);
        String text = "My name is ${name}. "
                + "My phone number: ${phoneNumber}. "
                + "I have recently downloaded Trojan program from this address: ${ipv4}. Don't advise."
                + "Yours respectfully, ${name}.";
        String expect = "My name is Donald. "
                + "My phone number: 8-999-765-00-11. "
                + "I have recently downloaded Trojan program from this address: 255.0.99.13. Don't advise."
                + "Yours respectfully, Donald.";
        Map<String, String> map = new HashMap<>(
                Map.of("${name}", "Donald",
                        "${phoneNumber}", "8-999-765-00-11",
                        "${ipv4}", "255.0.99.13"));
        String result;
        try {
            result = template.generate(text, map);
            assertThat(result, is(expect));
        } catch (NoKeysException nke) {
            assertThat(nke.getMessage(), is("Key is not exist!"));
        } catch (ExtraKeysException eke) {
            assertThat(eke.getMessage(), is("There are extra keys!"));
        }
    }

    @Test(expected = NoKeysException.class)
    public void whenMapHasNotNecessaryKeysThenOutputAppropriateException() throws ExtraKeysException, NoKeysException {
        Name name = new Name();
        PhoneNumber phoneNumber = new PhoneNumber();
        Ipv4 ipv4 = new Ipv4();
        Template template = new SimpleGenerator(name, phoneNumber, ipv4);
        String text = "My name is ${name}. "
                + "My phone number: ${phoneNumber}. "
                + "I have recently downloaded Trojan program from this address: ${ipv4}. Don't advise."
                + "Yours respectfully, ${name}.";
        Map<String, String> map = new HashMap<>(
                Map.of("${name}", "Morgan",
                        "${ipv4}", "250.0.255.99"));
        template.generate(text, map);
    }

    @Test(expected = ExtraKeysException.class)
    public void whenInputNameThenTemplateText() throws ExtraKeysException, NoKeysException {
        Name name = new Name();
        PhoneNumber phoneNumber = new PhoneNumber();
        Ipv4 ipv4 = new Ipv4();
        Template template = new SimpleGenerator(name, phoneNumber, ipv4);
        String text = "My name is ${name}. "
                + "My phone number: ${phoneNumber}. "
                + "I have recently downloaded Trojan program from this address: ${ipv4}. Don't advise."
                + "Yours respectfully, ${name}.";
        Map<String, String> map = new HashMap<>(
                Map.of("${name}", "Morgan",
                        "${phoneNumber}", "+79097650011",
                        "${ipv4}", "250.0.255.99",
                        "${surname}", "Freeman"));
        template.generate(text, map);
    }
}