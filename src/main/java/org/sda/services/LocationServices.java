package org.sda.services;

import org.sda.models.Location;

import java.util.List;
import java.util.UUID;

public interface LocationServices {

    void addLocation(Location location);
    void updateLocation(Location location);
    Location getLocationById(UUID id);
    List<Location> getAllLocations();
    void deleteLocation(Location location);
}
