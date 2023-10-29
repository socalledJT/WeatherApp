package org.sda.consoleApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sda.dao.LocationDAO;
import org.sda.dao.LocationDaoImpl;
import org.sda.dao.WeatherDataDAO;
import org.sda.dao.WeatherDataDAOImpl;
import org.sda.services.LocationServices;
import org.sda.services.LocationServicesImpl;
import org.sda.services.WeatherDataService;
import org.sda.services.WeatherDataServiceImpl;
import org.sda.util.HibernateUtil;

import javax.swing.*;

public class WeatherDataGui {

    private JFrame jFrame;
    private LocationServices locationServices;
    private WeatherDataService weatherDataService;

    public WeatherDataGui() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        LocationDAO locationDAO = new LocationDaoImpl(factory);
        WeatherDataDAO weatherDataDAO = new WeatherDataDAOImpl(factory);

        locationServices = new LocationServicesImpl(locationDAO);
        weatherDataService = new WeatherDataServiceImpl(weatherDataDAO);

    }

}
