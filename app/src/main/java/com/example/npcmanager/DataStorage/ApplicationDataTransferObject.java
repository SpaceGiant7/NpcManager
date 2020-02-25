package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class ApplicationDataTransferObject {
    private List<Location> locations;
    private List<Organization> organizations;
    private List<Person> people;
    private List<Quest> quests;

    public ApplicationDataTransferObject() {
        this.locations = ApplicationModels.getLocationModel().getAllItems();
        this.organizations = ApplicationModels.getOrganizationModel().getAllItems();
        this.people = ApplicationModels.getPersonModel().getAllItems();
        this.quests = ApplicationModels.getQuestModel().getAllItems();
    }

    public ApplicationDataTransferObject(
            List<Location> locations,
            List<Organization> organizations,
            List<Person> people,
            List<Quest> quests) {
        this.locations = locations;
        this.organizations = organizations;
        this.people = people;
        this.quests = quests;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Quest> getQuests() {
        return quests;
    }
}
