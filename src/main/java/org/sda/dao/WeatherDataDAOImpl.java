package org.sda.dao;

import org.sda.models.Location;
import org.sda.models.WeatherData;

import java.util.List;
import java.util.UUID;

public class WeatherDataDAOImpl implements WeatherDataDAO {
    @Override
    public void save(WeatherData weatherData) {

    }

    @Override
    public void update(WeatherData weatherData) {

    }

    @Override
    public WeatherData findById(UUID id) {
        return null;
    }

    @Override
    public List<WeatherData> findByLocation(Location location) {
        return null;
    }

    @Override
    public void delete(WeatherData weatherData) {

    }
}
