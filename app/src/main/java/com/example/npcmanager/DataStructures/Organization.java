package com.example.npcmanager.DataStructures;

import Constants.NpcConstants;

public class Organization implements BaseItem{

    private String name;
    private Location location;
    private String description;

    public Organization(String name, Location location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public static Organization None() {
        return new Organization(NpcConstants.NONE, Location.None(), "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return name;
    }

    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other )
            return true;

        if ( other == null || getClass() != other.getClass() )
            return false;

        Organization otherOrganization = (Organization)other;
        return name.equals( otherOrganization.name);
    }

}
