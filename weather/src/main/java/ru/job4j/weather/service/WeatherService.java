package ru.job4j.weather.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather.model.Weather;
import ru.job4j.weather.support.WeatherComparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.09.2020
 */

@Service
public class WeatherService {

    private final WeatherComparator comparator = new WeatherComparator();

    /**
     * Иммитация базы данных
     */

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();
    {
        weathers.put(1, new Weather(1, "Moscow", 17));
        weathers.put(2, new Weather(2, "Saint-Petersburg", 12));
        weathers.put(3, new Weather(3, "Bryansk", 19));
        weathers.put(4, new Weather(4, "Smolensk", 18));
        weathers.put(5, new Weather(5, "Kiev", 21));
        weathers.put(6, new Weather(6, "Minsk", 18));
    }

    /**
     * Метод находит данные о погоде конкретного города по идентификатору (реактивный стиль)
     * @param id - идентификатор
     * @return - данные о погоде
     */

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    /**
     * Метод находит данные о погоде всех городов (реактивный стиль)
     * @return - данные о погоде
     */

    public Flux<Weather> getAll() {
        return Flux.fromIterable(weathers.values());
    }

    /**
     * Метод находит данные о погоде конкретного города по максимальной температуре (реактивный стиль)
     * @return - данные о погоде
     */

    public Mono<Weather> findByMaxTemperature() {
        Weather elect = weathers.entrySet().stream().max(Map.Entry.comparingByValue(comparator)).orElseThrow(NoSuchElementException::new).getValue();
        return Mono.justOrEmpty(elect);
    }

    /**
     * Метод находит данные о погоде городов с температурой выше допустимого значения (реактивный стиль)
     * @param lower - допустимое значение
     * @return - данные о погоде
     */

    public Flux<Weather> findByLowerThresholdOfTemperature(Integer lower) {
        Map<Integer, Weather> elect = new ConcurrentHashMap<>();
        weathers.forEach((id, weather) -> {
            if (weather.getTemperature() > lower) {
                elect.put(id, weather);
            }
        });
        return Flux.fromIterable(elect.values());
    }
}
