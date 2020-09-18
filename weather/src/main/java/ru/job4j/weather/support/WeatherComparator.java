package ru.job4j.weather.support;

import ru.job4j.weather.model.Weather;
import java.util.Comparator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.09.2020
 */

public class WeatherComparator implements Comparator<Weather> {

    /**
     * Метод сравнивает температуру городов
     * @param firstWeather - температура одного города
     * @param secondWeather - температура другого города
     * @return - данные о погоде с максимальным значением температуры
     */

    @Override
    public int compare(Weather firstWeather, Weather secondWeather) {
        return Integer.compare(firstWeather.getTemperature(), secondWeather.getTemperature());
    }
}
