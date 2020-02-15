package com.example.npcmanager.DataStructures;

import java.util.Objects;

public class Location {
    private String name;

    private Location(String name ) {
        this.name = name;
    }

    public static Location of( String name ) {
        return new Location( name );
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals( Object other ) {
        if (this == other)
            return true;

        if ( other == null || getClass() != other.getClass() )
            return false;

        Location otherLocation = (Location) other;
        return name.equals( otherLocation.name );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
