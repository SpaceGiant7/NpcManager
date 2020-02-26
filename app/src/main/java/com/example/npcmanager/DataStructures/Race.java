package com.example.npcmanager.DataStructures;

import java.io.Serializable;
import java.util.Objects;

public class Race implements BaseItem, Serializable {
    private String name;
    private String description;

    private Race(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Race of(String name, String description) {
        return new Race(name, description);
    }

    public static Race None() {
        return Race.of(Constants.NpcConstants.NONE, "");
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

        Race otherRace = (Race) other;
        return name.equals(otherRace.name);
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
}
