package org.sda.consoleApp;

import org.sda.models.WeatherData;

import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {

    private JLabel temperatureLabel = new JLabel();
    private JLabel pressureLabel = new JLabel();
    private JLabel humidityLabel = new JLabel();
    private JLabel windSpeedLabel = new JLabel();
    private JLabel windDirectionLabel = new JLabel();

    private JLabel temperatureValueLabel = new JLabel();
    private JLabel pressureValueLabel = new JLabel();
    private JLabel humidityValueLabel = new JLabel();
    private JLabel windSpeedValueLabel = new JLabel();
    private JLabel windDirectionValueLabel = new JLabel();

    public WeatherPanel() {
        setLayout(new GridLayout(3, 2));
        add(temperatureLabel);
        add(temperatureValueLabel);
        add(pressureLabel);
        add(pressureValueLabel);
        add(humidityLabel);
        add(humidityValueLabel);
        add(windSpeedLabel);
        add(windSpeedValueLabel);
        add(windDirectionLabel);
        add(windDirectionValueLabel);
    }

    public void updateWeatherData(WeatherData weatherData) {
        temperatureValueLabel.setText("Temperature: " + weatherData.getTemperature());
        pressureValueLabel.setText("Pressure: " + weatherData.getPressure());
        humidityValueLabel.setText("Humidity: " + weatherData.getHumidity());
        windSpeedValueLabel.setText("Wind Speed: " + weatherData.getWindSpeed());
        windDirectionValueLabel.setText("Wind Direction: " + weatherData.getWindDirection());
    }

}
