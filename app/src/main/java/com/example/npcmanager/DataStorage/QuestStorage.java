package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

import java.util.Objects;

public class QuestStorage {
    private String questName;
    private PersonStorage questGiver;
    private Location returnLocation;
    private String details;

    public QuestStorage( String questName, PersonStorage questGiver, Location returnLocation, String details ) {
        this.questName = questName;
        this.questGiver = questGiver;
        this.returnLocation = returnLocation;
        this.details = details;
    }

    public QuestStorage( Quest quest ) {
        this.questName = quest.getQuestName();
        this.questGiver =  new PersonStorage( quest.getQuestGiver() );
        this.returnLocation = quest.getReturnLocation();
        this.details = quest.getDetails();
    }

    public void setQuestName( String questName ) {
        this.questName = questName;
    }

    public void setQuestGiver( PersonStorage questGiver ) {
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

    public PersonStorage getQuestGiver() {
        return questGiver;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public String getDetails() {
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
        QuestStorage quest = (QuestStorage) other;
        return Objects.equals( questName, quest.questName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( questName );
    }
}
