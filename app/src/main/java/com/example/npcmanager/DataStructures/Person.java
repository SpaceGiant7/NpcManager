package com.example.npcmanager.DataStructures;


import com.example.npcmanager.DataStorage.PersonStorage;

public class Person {
    private String name;
    private Race race;
    private Gender gender;
    private Location home;
    private Occupation occupation;
    private Organization organization;
    private boolean isDeceased;
    private String description;

    public Person( String name, Race race, Gender gender, Location home,
                   Occupation occupation, Organization organization, boolean isDeceased, String description ) {
        this.name = name;
        this.home = home;
        this.race = race;
        this.gender = gender;
        this.occupation = occupation;
        this.organization = organization;
        this.isDeceased = isDeceased;
        this.description = description;
    }

    public Person( PersonStorage personStorage ) {
        this.name = personStorage.getName();
        this.home = personStorage.getHome();
        this.race = personStorage.getRace();
        this.gender = personStorage.getGender();
        this.occupation = personStorage.getOccpation();
        this.organization = personStorage.getOrganization();
        this.isDeceased = personStorage.getIsDeceased();
        this.description = personStorage.getDescription();
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

    public void setName( String newName ) {
        name = newName;
    }

    public void setRace( Race newRace ) {
        race = newRace;
    }

    public void setGender( Gender newGender ) {
        gender = newGender;
    }

    public void setHome( Location newHome ) {
        home = newHome;
    }

    public void setOccupation( Occupation newOccupation ) {
        occupation = newOccupation;
    }

    public void setOrganization( Organization newOrganization ) {
        organization = newOrganization;
    }

    public void setIsDeceased( boolean newIsDeceased ) {
        isDeceased = newIsDeceased;
    }

    public void setDescription( String newDescription ) {
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
    public boolean equals( Object other ) {
        if ( this == other )
            return true;

        if ( !(other instanceof Person ) )
            return false;

        Person otherPerson = (Person)other;
        return name.equals( otherPerson.getName() ) &&
                home.equals( otherPerson.getHome() );
    }

    public String toString() {
        return name;
    }
}
