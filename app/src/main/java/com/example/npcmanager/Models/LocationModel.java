package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;

public class LocationModel extends BaseModel<Location>{
    protected Location getNone() {
        return Location.None();
    }
}
