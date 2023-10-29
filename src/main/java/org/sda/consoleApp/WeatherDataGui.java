package org.sda.consoleApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.sda.dao.LocationDAO;
import org.sda.dao.LocationDaoImpl;
import org.sda.dao.WeatherDataDAO;
import org.sda.dao.WeatherDataDAOImpl;
import org.sda.models.Location;
import org.sda.models.WeatherData;
import org.sda.services.LocationServices;
import org.sda.services.LocationServicesImpl;
import org.sda.services.WeatherDataService;
import org.sda.services.WeatherDataServiceImpl;
import org.sda.util.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WeatherDataGui {

    private JFrame jFrame;
    private LocationServices locationServices;
    private WeatherDataService weatherDataService;
    private JTextField cityField;
    private final String apiKey = "a475956f51851932d5dd2b66dce5f692";

    private JPanel createLocationPanel() {
        //Create a new JPanel
        JPanel panel = new JPanel();
        //Set layout Manager
        panel.setLayout(new GridLayout(5, 2));

        //Create Text Fields
        JTextField latitudeField = new JTextField();
        JTextField longitudeField = new JTextField();
        JTextField regionField = new JTextField();
        JTextField countryField = new JTextField();

        //Create a Button
        JButton addButton = new JButton("Add Location");

        addButton.addActionListener(e -> {
            try {
                double latitude = Double.parseDouble(latitudeField.getText());
                double longitude = Double.parseDouble(longitudeField.getText());
                String region = regionField.getText();
                String country = countryField.getText();

                Location newLocation = new Location(region, country, latitude, longitude);

                //Add new Location
                locationServices.addLocation(newLocation);

                JOptionPane.showMessageDialog(jFrame, "Location Added Successfully!");
            } catch (NumberFormatException numEx) {
                JOptionPane.showMessageDialog(jFrame, "Invalid Input, please enter " +
                        "valid latitude & longitude!");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jFrame, "Error Adding Location!" + e1.getMessage());
            }
        });

        panel.add(new JLabel("Region: "));
        panel.add(regionField);
        panel.add(new JLabel("Country: "));
        panel.add(countryField);
        panel.add(new JLabel("Latitude: "));
        panel.add(latitudeField);
        panel.add(new JLabel("Longitude: "));
        panel.add(longitudeField);
        panel.add(addButton);

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        List<Location> locations = locationServices.getAllLocations();

        DefaultListModel<Location> listModel = new DefaultListModel<>();
        for (Location l : locations) {
            listModel.addElement(l);
        }

        JList<Location> locationList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(locationList);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private WeatherData retrieveWeatherDataFromAPI(String cityField, String apiKey) {
        int responseCode = 0;

        try {
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                    + cityField + "&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection
                        .getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                double temperatureInKelvin = jsonResponse.getJSONObject("main").getDouble("temp");
                double temperatureInCelsius = temperatureInKelvin - 273.15; // Convert to Celsius

                double pressure = jsonResponse.getJSONObject("main").getDouble("pressure");
                double humidity = jsonResponse.getJSONObject("main").getDouble("humidity");
                double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed");
                double windDirection = jsonResponse.getJSONObject("wind").getDouble("deg");

                // Create a WeatherData object and populate it
                WeatherData weatherData = new WeatherData();
                weatherData.setTemperature(temperatureInCelsius); // Use the converted temperature in Celsius
                weatherData.setPressure(pressure);
                weatherData.setHumidity(humidity);
                weatherData.setWindSpeed(windSpeed);
                weatherData.setWindDirection(windDirection);

                return weatherData;
            } else {
                System.out.println("Failed to retrieve weather data. HTTP error code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching weather data.");
            System.out.println("HTTP error code: " + responseCode);
        }

        return null; // Return null if retrieval fail
    }

    private JPanel createDownloadPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        cityField = new JTextField();
        JButton downloadButton = new JButton("Download Data");
        WeatherPanel weatherPanel = new WeatherPanel();

        downloadButton.addActionListener(e -> {
            String city = cityField.getText();
            WeatherData weatherData = retrieveWeatherDataFromAPI(city, apiKey);

            if (weatherData != null) {
                weatherPanel.updateWeatherData(weatherData);
                weatherDataService.addWeatherData(weatherData);
                JOptionPane.showMessageDialog(jFrame, "Weather data added Successfully!");
            } else {
                JOptionPane.showMessageDialog(jFrame, "Failed to retrieve Weather Data!");
            }
        });

        panel.add(new JLabel("City"));
        panel.add(cityField);
        panel.add(downloadButton);
        panel.add(weatherPanel);

        return panel;
    }

    public WeatherDataGui() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        LocationDAO locationDAO = new LocationDaoImpl(factory);
        WeatherDataDAO weatherDataDAO = new WeatherDataDAOImpl(factory);

        locationServices = new LocationServicesImpl(locationDAO);
        weatherDataService = new WeatherDataServiceImpl(weatherDataDAO);

        jFrame = new JFrame("Weather Data App!");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
        jFrame.setLayout(new BorderLayout());

        JTabbedPane jTabbedPane = new JTabbedPane();

        JPanel locationPlanel = createLocationPanel();
        JPanel displayPanel = createDisplayPanel();
        JPanel downloadPanel = createDownloadPanel();

        jTabbedPane.add("Add Location", locationPlanel);
        jTabbedPane.add("Display Location", displayPanel);
        jTabbedPane.add("Download Weather Data", downloadPanel);

        jFrame.add(jTabbedPane, BorderLayout.CENTER);

    }

    public void display() {
        jFrame.setVisible(true);
    }

}