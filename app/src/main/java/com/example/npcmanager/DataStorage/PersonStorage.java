package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;

public class PersonStorage {
    private String name;
    private Race race;
    private Gender gender;
    private Location home;
    private Occupation occupation;
    private Organization organization;
    private boolean isDeceased;
    private String description;

    public PersonStorage( String name, Race race, Gender gender, Location home,
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

    public PersonStorage( Person person ) {

        this.name = person.getName();
        this.race = person.getRace();
        this.gender = person.getGender();
        this.home = person.getHome();
        this.occupation = person.getOccpation();
        this.organization = person.getOrganization();
        this.isDeceased = person.getIsDeceased();
        this.description = person.getDescription();
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
        return name + " | " + home;
    }
}
