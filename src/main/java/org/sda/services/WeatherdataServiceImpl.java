package org.sda.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sda.dao.WeatherDataDAO;
import org.sda.models.Location;
import org.sda.models.WeatherData;

import java.util.List;

import static org.sda.util.HibernateUtil.sessionFactory;

public class WeatherdataServiceImpl implements WeatherdataService {

    private WeatherDataDAO weatherDataDAO;
    @Override
    public void addWeatherData(WeatherData weatherData) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(weatherData);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
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
