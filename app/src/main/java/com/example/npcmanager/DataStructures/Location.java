package com.example.npcmanager.DataStructures;

import com.example.npcmanager.Constants.NpcConstants;

import java.io.Serializable;
import java.util.Objects;

public class Location implements BaseItem, Serializable {
    private String name;
    private String description;

    private Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Location of(String name, String description) {
        return new Location(name, description);
    }

    public static Location None() {
        return Location.of(NpcConstants.NONE, "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        Location otherLocation = (Location) other;
        return name.equals(otherLocation.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean isNone() {
        return this.equals(Location.None());
    }
}
