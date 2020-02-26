package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

class ApplicationDataTransferObject {
    private List<Person> people;
    private List<Quest> quests;
    private List<Organization> organizations;
    private List<Location> locations;
    private List<Race> races;
    private List<Occupation> occupations;
    private List<Gender> genders;

    ApplicationDataTransferObject() {
        this.people = ApplicationModels.getPersonModel().getAllItems();
        this.quests = ApplicationModels.getQuestModel().getAllItems();
        this.organizations = ApplicationModels.getOrganizationModel().getAllItems();
        this.locations = ApplicationModels.getLocationModel().getAllItems();
        this.races = ApplicationModels.getRaceModel().getAllItems();
        this.occupations = ApplicationModels.getOccupationModel().getAllItems();
        this.genders = ApplicationModels.getGenderModel().getAllItems();
    }

    List<Person> getPeople() {
        return people;
    }

    List<Quest> getQuests() {
        return quests;
    }

    List<Organization> getOrganizations() {
        return organizations;
    }

    List<Location> getLocations() {
        return locations;
    }

    List<Race> getRaces() {
        return races;
    }

    List<Occupation> getOccupations() {
        return occupations;
    }

    List<Gender> getGenders() {
        return genders;
    }
}
