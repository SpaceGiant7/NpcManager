package com.example.npcmanager.DataStructures;

public enum PersonTrait {
    GENDER("Gender"),
    LOCATION("Location"),
    MORTALITY("Mortality"),
    NAME("Name"),
    OCCUPATION("Occupation"),
    ORGANIZATION("Organization"),
    RACE("Race");

    private String name;

    PersonTrait(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
