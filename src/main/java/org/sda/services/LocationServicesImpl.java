package org.sda.services;

import org.sda.dao.LocationDAO;
import org.sda.models.Location;

import java.util.List;
import java.util.UUID;

public class LocationServicesImpl implements LocationServices {

    private LocationDAO locationDao;

    public LocationServicesImpl(LocationDAO locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) {
        locationDao.save(location);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.update(location);
    }

    @Override
    public Location getLocationById(UUID id) {
        return locationDao.findById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.findAll();
    }

    @Override
    public void deleteLocation(Location location) {
        locationDao.delete(location);
    }
}
