package com.example.npcmanager.DataStructures;

import com.example.npcmanager.Constants.NpcConstants;

import java.io.Serializable;
import java.util.Objects;


public class Gender implements BaseItem, Serializable {
    private String name;

    private Gender(String name) {
        this.name = name;
    }

    public static Gender of(String name) {
        return new Gender(name);
    }

    public static Gender None() {
        return Gender.of(NpcConstants.NONE);
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        Gender otherGender = (Gender) other;
        return name.equals(otherGender.name);
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

