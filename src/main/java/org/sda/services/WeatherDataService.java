package org.sda.services;

import org.sda.models.Location;
import org.sda.models.WeatherData;

import java.util.List;

public interface WeatherDataService {

    void addWeatherData(WeatherData weatherData);
    void updateWeatherData(WeatherData weatherData);
    WeatherData getWeatherDataById(Long id);
    List<WeatherData> getWeatherdataByLocation(Location location);
}
