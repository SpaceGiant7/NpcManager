package com.example.npcmanager.DataStructures;


import com.example.npcmanager.Constants.NpcConstants;

import java.io.Serializable;

public class Person implements BaseItem, Serializable {
    private String name;
    private Race race;
    private Gender gender;
    private Location home;
    private Occupation occupation;
    private Organization organization;
    private Mortality mortality;
    private String description;

    public Person(
            String name,
            Race race,
            Gender gender,
            Location home,
            Occupation occupation,
            Organization organization,
            Mortality isDeceased,
            String description) {
        this.name = name;
        this.race = race;
        this.home = home;
        this.gender = gender;
        this.occupation = occupation;
        this.organization = organization;
        this.mortality = isDeceased;
        this.description = description;
    }

    public static Person None() {
        return new Person(
                NpcConstants.NONE,
                Race.None(),
                Gender.None(),
                Location.None(),
                Occupation.None(),
                Organization.None(),
                Mortality.NONE,
                "");
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public Gender getGender() {
        return gender;
    }

    public Location getHome() {
        return home;
    }

    public Occupation getOccpation() {
        return occupation;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Mortality getMortality() {
        return mortality;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setRace(Race newRace) {
        race = newRace;
    }

    public void setGender(Gender newGender) {
        gender = newGender;
    }

    public void setHome(Location newHome) {
        home = newHome;
    }

    public void setOccupation(Occupation newOccupation) {
        occupation = newOccupation;
    }

    public void setOrganization(Organization newOrganization) {
        organization = newOrganization;
    }

    public void setMortality(Mortality newMortality) {
        mortality = newMortality;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof Person)) return false;

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.getName()) && home.equals(otherPerson.getHome());
    }

    public String toString() {
        return name;
    }

    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean isNone() {
        return this.equals(Person.None());
    }
}
