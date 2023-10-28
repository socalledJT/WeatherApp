package org.sda.dao;

import org.sda.models.Location;

import java.util.List;
import java.util.UUID;

public interface LocationDAO {

    void save(Location location);
    void update(Location location);
    Location findById(UUID id);
    List<Location> findAll();
    void delete(Location location);
}
