package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;

public class OrganizationModel extends BaseModel<Organization>{

    public OrganizationModel() {
        super();
    }

    protected Organization getNone() {
        return Organization.None();
    }

    public void replaceLocation(String oldLocationName, Location newLocation) {
        getAllItems()
                .stream()
                .filter(o -> o.getLocation().getName().equals(oldLocationName))
                .forEach(o -> o.setLocation(newLocation));
    }
}
