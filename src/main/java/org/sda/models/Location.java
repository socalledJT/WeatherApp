package org.sda.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String region;

    private String country;

    private Double longitude;

    private Double latitude;

    public Location() {}

    public Location(UUID id, String region, String country, Double longitude, Double latitude) {
        this.id = id;
        this.region = region;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(String region, String country, Double longitude, Double latitude) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id: " + id +
                "\n\tregion: " + region +
                "\n\tcountry: '" + country +
                "\n\tlongitude: " + longitude +
                "\n\tlatitude: " + latitude +
                '}';
    }
}
