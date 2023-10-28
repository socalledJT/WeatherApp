package org.sda.services;

import org.sda.dao.WeatherDataDAO;
import org.sda.models.Location;
import org.sda.models.WeatherData;

import java.util.List;

public class WeatherdataServiceImpl implements WeatherdataService {

    private WeatherDataDAO weatherDataDAO;
    @Override
    public void addWeatherData(WeatherData weatherData) {
        weatherDataDAO.save(weatherData);
    }

    @Override
    public void updateWeatherData(WeatherData weatherData) {
        weatherDataDAO.update(weatherData);
    }

    @Override
    public WeatherData getWeatherDataById(Long id) {
        return weatherDataDAO.findById(id);
    }

    @Override
    public List<WeatherData> getWeatherdataByLocation(Location location) {
        return weatherDataDAO.findByLocation(location);
    }
}
