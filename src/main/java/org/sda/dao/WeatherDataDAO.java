package org.sda.dao;

import org.sda.models.Location;
import org.sda.models.WeatherData;

import java.util.List;
import java.util.UUID;

public interface WeatherDataDAO {

    void save(WeatherData weatherData);
    void update(WeatherData weatherData);
    WeatherData findById(Long id);
    List<WeatherData> findByLocation(Location location);
    void delete(WeatherData weatherData);
}
