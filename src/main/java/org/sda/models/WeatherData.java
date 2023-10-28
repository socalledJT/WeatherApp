package org.sda.models;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Double temperature;

    private Double humidity;

    private Double pressure;

    private Double windSpeed;

    private Double windDirection;

    public WeatherData() {
    }

    public WeatherData(Long id, Date date, Double temperature, Double humidity, Double pressure, Double windSpeed, Double windDirection) {
        this.id = id;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id: " + id + "\n\t" +
                "\n\tdate: " + date +
                "\n\ttemperature: " + temperature +
                "\n\thumidity: " + humidity +
                "\n\tpressure: " + pressure +
                "\n\twindSpeed: " + windSpeed +
                "\n\twindDirection: " + windDirection +
                '}';
    }
}
