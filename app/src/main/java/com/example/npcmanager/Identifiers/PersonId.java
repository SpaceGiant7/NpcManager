package com.example.npcmanager.Identifiers;

import com.example.npcmanager.DataStructures.*;

import java.util.Objects;

public class PersonId {
    private final String name;
    private final Location home;
    private final Occupation occupation;

    public PersonId (String name, Location home, Occupation occupation) {
        this.name = name;
        this.home = home;
        this.occupation = occupation;
    }

    public PersonId withName( String newName ) {
        return new PersonId( newName, home, occupation );
    }

    public PersonId withHome( Location newHome ) {
        return new PersonId( name, newHome, occupation );
    }

    public PersonId withOccupation( Occupation newOccupation ) {
        return new PersonId( name, home, newOccupation );
    }

    public String getName() {
        return name;
    }

    public Location getHome() {
        return home;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    @Override
    public String toString() {
        return name + '_' + home + '_' + occupation;
    }

    @Override
    public boolean equals( Object other ) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        PersonId otherPersonId = (PersonId) other;
        return name.equals( otherPersonId.name ) &&
                home.equals( otherPersonId.home ) &&
                occupation == otherPersonId.occupation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, home, occupation);
    }
}
