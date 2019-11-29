package ru.job4j.generator;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 29.11.2019
 */

public class TemplateTest {
    @Test
    public void whenInputCorrectDataThenOutputTemplateText() {
        Template template = new SimpleGenerator();
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
        String result = null;
        try {
            result = template.generate(text, map);
            assertThat(result, is(expect));
        } catch (NoKeysException nke) {
            assertThat(nke.getMessage(), is("Such keys are not exist!"));
        } catch (NotCorrectFormatException ncfe) {
            assertThat(ncfe.getMessage(), is("There is not correct format!"));
        } catch (ExtraKeysException eke) {
            assertThat(eke.getMessage(), is("There are extra keys!"));
        }
    }

    @Test(expected = NotCorrectFormatException.class)
    public void whenInputIncorrectFormatOfDataThenOutputAppropriateException() throws ExtraKeysException, NoKeysException, NotCorrectFormatException {
        Template template = new SimpleGenerator();
        String text = "My name is ${name}. "
                + "My phone number: ${phoneNumber}. "
                + "I have recently downloaded Trojan program from this address: ${ipv4}. Don't advise."
                + "Yours respectfully, ${name}.";
        Map<String, String> map = new HashMap<>(
                Map.of("${name}", "donald",
                        "${phoneNumber}", "9-9909-765-00-11",
                        "${ipv4}", "255.0*.99.13"));
        template.generate(text, map);
    }

    @Test(expected = NoKeysException.class)
    public void whenMapHasNotNecessaryKeysThenOutputAppropriateException() throws ExtraKeysException, NoKeysException, NotCorrectFormatException {
        Template template = new SimpleGenerator();
        String text = "My name is ${name}. "
                + "My phone number: ${phoneNumber}. "
                + "I have recently downloaded Trojan program from this address: ${ipv4}. Don't advise."
                + "Yours respectfully, ${name}.";
        Map<String, String> map = new HashMap<>(
                Map.of("${name}", "Morgan",
                        "${number}", "+79097650011",
                        "${ipv4}", "250.0.255.99"));
        template.generate(text, map);
    }

    @Test(expected = ExtraKeysException.class)
    public void whenInputNameThenTemplateText() throws ExtraKeysException, NoKeysException, NotCorrectFormatException {
        Template template = new SimpleGenerator();
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