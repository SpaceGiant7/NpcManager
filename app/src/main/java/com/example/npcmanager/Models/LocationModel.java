package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;

import java.util.List;
import java.util.Optional;

public class LocationModel extends BaseModel {

    public LocationModel() {
        super();
    }

    protected Location getNone() {
        return Location.None();
    }

    public void addLocation(Location newLocation) {
        addItem(newLocation);
    }

    public void removeLocationIfExists(String locationName) {
        findFirstLocationMaybe(locationName).ifPresent(this::removeItem);
    }

    public Optional<Location> findFirstLocationMaybe(String locationName) {
        return getAllLocations().stream()
                .filter(t -> t.getIdentifier()
                        .equals(locationName))
                .findFirst();
    }

    public List<Location> getAllLocations() {
        return getList();
    }

}
