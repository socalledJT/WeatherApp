package org.sda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sda.models.Location;
import org.sda.models.WeatherData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class WeatherDataDAOImpl implements WeatherDataDAO {

    private SessionFactory sessionFactory;

    public WeatherDataDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(WeatherData weatherData) {
        Session session = sessionFactory.getCurrentSession();
        session.save(weatherData);
        System.out.println("Weather data added!");
    }

    @Override
    public void update(WeatherData weatherData) {
        Session session = sessionFactory.getCurrentSession();
        session.update(weatherData);
        System.out.println("WeatherData updated!");
    }

    @Override
    public WeatherData findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(WeatherData.class, id);
    }

    @Override
    public List<WeatherData> findByLocation(Location location) {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<WeatherData> query = builder.createQuery(WeatherData.class);
            Root<WeatherData> root = query.from(WeatherData.class);
            query.select(root).where(builder.equal(root.get("locations"), location));

            Query<WeatherData> q = session.createQuery(query);
            return q.getResultList();
        }
    }

    @Override
    public void delete(WeatherData weatherData) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(weatherData);
        System.out.println("WeatherData deleted!");
    }
}
