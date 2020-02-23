package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;

import java.util.List;
import java.util.Optional;

public class LocationModel extends BaseModel {

    public LocationModel() {
        super();
        addLocation(Location.None());
    }

    public void addLocation(Location newLocation) {
        addItem(newLocation);
    }

    public void removeLocationIfExists(String locationName) {
        getLocationMaybe(locationName).ifPresent(this::removeItem);
    }

    public Optional<Location> getLocationMaybe(String locationName) {
        return getAllLocations().stream()
                .filter(t -> t.getIdentifier()
                        .equals(locationName))
                .findFirst();
    }

    public List<Location> getAllLocations() {
        return getList();
    }

}
