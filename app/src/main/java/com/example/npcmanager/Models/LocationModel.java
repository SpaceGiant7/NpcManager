package com.example.npcmanager.Models;

import Constants.NpcConstants;
import com.example.npcmanager.DataStructures.Location;

import java.util.*;

public class LocationModel extends BaseModel<Location> {

    public LocationModel() {
        super();
        addLocation( Location.of( NpcConstants.NONE ) );
    }

    public void addLocation(Location newLocation) {
        addItem(newLocation);
    }

    public Optional<Location> getLocation(String locationName) {
        return list.stream()
                .filter(t -> t.getName()
                    .equals(locationName))
                .findFirst();
    }

    public List<Location> getAllLocations() {
        return list;
    }

    public void removeLocation(String locationName ) {
        removeItem( Location.of( locationName ) );
    }

    public int getNumberOfLocations() {
        return getNumberOfItems();
    }

    public Boolean locationExists(String locationName ) {
        return itemExists( Location.of( locationName ) );
    }
}
