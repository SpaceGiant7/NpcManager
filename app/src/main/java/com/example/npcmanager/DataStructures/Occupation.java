package com.example.npcmanager.DataStructures;

import com.example.npcmanager.Constants.NpcConstants;

import java.io.Serializable;
import java.util.Objects;

public class Occupation implements BaseItem, Serializable {
    private String name;
    private String description;

    public Occupation(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Occupation of(String name, String description) {
        return new Occupation(name, description);
    }

    public static Occupation None() {
        return Occupation.of(NpcConstants.NONE, "");
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

        Occupation otherOccupation = (Occupation) other;
        return name.equals(otherOccupation.name);
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
