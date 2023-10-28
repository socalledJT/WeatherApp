package org.sda.services;

import org.sda.models.WeatherData;

import java.util.List;

public interface WeatherdataService {

    void addWeatherData(WeatherData weatherData);
    void updateWeatherData(WeatherData weatherData);
    WeatherData getWeatherDataById(Long id);
    List<WeatherData> getAllWeatherdata();
}
