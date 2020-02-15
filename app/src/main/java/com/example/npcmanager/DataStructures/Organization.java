package com.example.npcmanager.DataStructures;

public class Organization {

    private String organizationName;
    private Location location;

    public Organization( String organizationName, Location location ) {
        this.organizationName = organizationName;
        this.location = location;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString() {
        return organizationName;
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other )
            return true;

        if ( other == null || getClass() != other.getClass() )
            return false;

        Organization otherOrganization = (Organization)other;
        return organizationName.equals( otherOrganization.organizationName );
    }

}
