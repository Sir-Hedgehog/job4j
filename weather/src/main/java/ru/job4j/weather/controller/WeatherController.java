package ru.job4j.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather.model.Weather;
import ru.job4j.weather.service.WeatherService;
import java.time.Duration;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.09.2020
 */

@RestController
public class WeatherController {
    private final WeatherService weathers;

    @Autowired
    public WeatherController(WeatherService weathers) {
        this.weathers = weathers;
    }

    /**
     * Метод отображает результат запроса по получению данных о погоде среди всех городов
     * @return - данные о погоде
     */

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> getAll() {
        Flux<Weather> data = weathers.getAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    /**
     * Метод отображает результат запроса по получению данных о погоде по идентификатору
     * @param id - идентификатор
     * @return - данные о погоде
     */

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable Integer id) {
        return weathers.findById(id);
    }

    /**
     * Метод отображает результат запроса по получению данных о погоде с максимальной температурой
     * @return - данные о погоде
     */

    @GetMapping(value = "/hottest")
    public Mono<Weather> getMax() {
        return weathers.findByMaxTemperature();
    }

    /**
     * Метод отображает результат запроса по получению данных о погоде с температурой выше допустимого значения
     * @param lower - допустимое значение
     * @return - данные о погоде
     */

    @GetMapping(value = "/cityGreatThen/{lower}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all(@PathVariable Integer lower) {
        Flux<Weather> data = weathers.findByLowerThresholdOfTemperature(lower);
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
}
