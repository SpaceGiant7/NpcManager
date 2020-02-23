package com.example.npcmanager.DataStructures;

import Constants.NpcConstants;

public class Person implements BaseItem{
    private String name;
    private Race race;
    private Gender gender;
    private Location home;
    private Occupation occupation;
    private Organization organization;
    private boolean isDeceased;
    private String description;

    public Person(
            String name,
            Race race,
            Gender gender,
            Location home,
            Occupation occupation,
            Organization organization,
            boolean isDeceased,
            String description) {
        this.name = name;
        this.race = race;
        this.home = home;
        this.gender = gender;
        this.occupation = occupation;
        this.organization = organization;
        this.isDeceased = isDeceased;
        this.description = description;
    }

    public static Person None() {
        return new Person(
                NpcConstants.NONE,
                Race.UNKNOWN,
                Gender.UNKNOWN,
                Location.None(),
                Occupation.NONE,
                Organization.None(),
                false,
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

    public boolean getIsDeceased() {
        return isDeceased;
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

    public void setIsDeceased(boolean newIsDeceased) {
        isDeceased = newIsDeceased;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public String getNamePropperty() {
        return name;
    }

    public Race getRacePropperty() {
        return race;
    }

    public Gender getGenderPropperty() {
        return gender;
    }

    public Location getHomePropperty() {
        return home;
    }

    public Occupation getOccpationPropperty() {
        return occupation;
    }

    public Organization getOrganizationPropperty() {
        return organization;
    }

    public boolean getIsDeceasedPropperty() {
        return isDeceased;
    }

    public String getDescriptionPropperty() {
        return description;
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
}
