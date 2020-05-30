package ru.job4j.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.example.service.EventService;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 29.05.2020
 */

@Controller
public class IndexControl {

    private final EventService events = EventService.getInstance();

    /**
     * Метод обрабатывает запрос и передает данные в файл index.jsp
     * @param model - модель
     * @return - список событий для jsp-файла (accidents.jsp)
     */

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("events", events.getData());
        return "accidents";
    }
}
