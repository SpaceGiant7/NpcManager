package com.example.npcmanager.DataStructures;

import java.io.Serializable;
import java.util.Objects;

import Constants.NpcConstants;

public class Quest implements BaseItem, Serializable {
    private String questName;
    private Person questGiver;
    private Location returnLocation;
    private String details;

    public Quest( String questName, Person questGiver, Location returnLocation, String details ) {
        this.questName = questName;
        this.questGiver = questGiver;
        this.returnLocation = returnLocation;
        this.details = details;
    }

    public static Quest None() {
        return new Quest(NpcConstants.NONE, Person.None(), Location.None(), "");
    }

    public void setQuestName( String questName ) {
        this.questName = questName;
    }

    public void setQuestGiver( Person questGiver ) {
        this.questGiver = questGiver;
    }

    public void setReturnLocation( Location returnLocation ) {
        this.returnLocation = returnLocation;
    }

    public void setDetails( String details ) {
        this.details = details;
    }

    public String getQuestName() {
        return questName;
    }

    public Person getQuestGiver() {
        return questGiver;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public String getDetails() {
        return details;
    }

    public String getQuestNameProperty() {
        return questName;
    }

    public Person getQuestGiverProperty() {
        return questGiver;
    }

    public Location getReturnLocationProperty() {
        return returnLocation;
    }

    public String getDetailsProperty() {
        return details;
    }

    @Override
    public String toString() {
        return questName;
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other )
            return true;
        if ( other == null || getClass() != other.getClass())
            return false;
        Quest quest = (Quest) other;
        return Objects.equals( questName, quest.questName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( questName );
    }

    public String getIdentifier() {
        return questName;
    }
}
