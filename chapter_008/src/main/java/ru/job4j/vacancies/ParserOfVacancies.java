package ru.job4j.vacancies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 23.08.2019
 */

public class ParserOfVacancies {

    public static class Template {
        private String name;
        private String text;
        private String link;
        private String id;

        public Template(String name, String text, String link) {
            this.name = name;
            this.text = text;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public ParserOfVacancies() {
    }

    public List<Template> saveVacancies() throws IOException {
        List<Template> list = new ArrayList<>();
        for (int index = 1; index < 50; index++) {
            list.addAll(this.saveTd(this.saveDates(index), index));
        }
        return list;
    }

    private Elements saveDates(int index) throws IOException {
        Document document = Jsoup.connect("https://www.sql.ru/forum/job/" + index).get();
        Elements elementsOfDate = document.getElementsByAttributeValue("class", "altCol");
        Elements newElementsOfDate = new Elements();
        for (int number = 0; number < elementsOfDate.size(); number++) {
            if (number % 2 != 0) {
                newElementsOfDate.add(elementsOfDate.get(number));
            }
        }
        Elements filter = new Elements();
        for (Element element : newElementsOfDate) {
            if (element.text().contains("вчера, ")
                    || element.text().contains("сегодня, ")
                    || element.text().contains(" 19, ")) {
                filter.add(element);
            }
        }
        return filter;
    }

    private Set<Template> saveTd (Elements newElementsOfDate, int index) throws IOException {
        Set<Template> set = new LinkedHashSet<>();
        List<Template> list = new ArrayList<>();
        Document document = Jsoup.connect("https://www.sql.ru/forum/job/" + index).get();
        Elements elementsOfTd = document.getElementsByAttributeValue("class", "postslisttopic");
        for (int number = 0; number < newElementsOfDate.size(); number++) {
            Element elementOfHref = elementsOfTd.get(number).child(0);
            if (elementOfHref.text().toLowerCase().contains("java")
                    && !elementOfHref.text().toLowerCase().contains("script")) {
                String link = elementOfHref.attr("href");
                String name = elementsOfTd.get(number).child(0).text();
                Document description = Jsoup.connect(link).get();
                Elements messages = description.getElementsByAttributeValue("class", "msgBody");
                String text = messages.get(1).text();
                list.add(new Template(name, text, link));
            }
            set.addAll(list);
        }
        return set;
    }
}
