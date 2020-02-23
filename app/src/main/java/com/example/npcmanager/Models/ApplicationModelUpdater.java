package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

public class ApplicationModelUpdater {

    public static void addPerson(Person person) {
        ApplicationModels.getPersonModel().addPerson(person);
    }

    public static void removePerson(String personName) {
        replacePersonInModels(personName, Person.None());
    }

    public static void replacePerson(String oldPersonName, Person newPerson) {
        replacePersonInModels(oldPersonName, newPerson);
        addPerson(newPerson);
    }

    private static void replacePersonInModels(String oldPersonName, Person newPerson) {
        ApplicationModels.getQuestModel().replacePerson(oldPersonName, newPerson);
        ApplicationModels.getPersonModel().removePersonIfExists(oldPersonName);
    }

    public static void addQuest(Quest quest) {
        ApplicationModels.getQuestModel().addQuest(quest);
    }

    public static void removeQuest(String questName) {
        ApplicationModels.getQuestModel().removeQuestIfExists(questName);
    }

    public static void replaceQuest(String oldQuestName, Quest newQuest) {
        removeQuest(oldQuestName);
        addQuest(newQuest);
    }

    public static void addLocation(Location location) {
        ApplicationModels.getLocationModel().addLocation(location);
    }

    public static void removeLocation(String locationName) {
        replaceLocationInModels(locationName, Location.None());
    }

    public static void replaceLocation(String oldLocationName, Location newLocation) {
        replaceLocationInModels(oldLocationName, newLocation);
        addLocation(newLocation);
    }

    private static void replaceLocationInModels(String oldLocationName, Location newLocation) {
        ApplicationModels.getPersonModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getQuestModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getOrganizationModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getLocationModel().removeLocationIfExists(oldLocationName);
    }

    public static void addOrganization(Organization organization) {
        ApplicationModels.getOrganizationModel().addOrganization(organization);
    }

    public static void removeOrganization(String organizationName) {
        replaceOrganizationInModels(organizationName, Organization.None());
    }

    public static void replaceOrganization(
            String oldOrganizationName, Organization newOrganization) {
        replaceOrganizationInModels(oldOrganizationName, newOrganization);
        addOrganization(newOrganization);
    }

    private static void replaceOrganizationInModels(
            String oldOrganizationName, Organization newOrganization) {
        ApplicationModels.getPersonModel()
                .replaceOrganization(oldOrganizationName, newOrganization);
        ApplicationModels.getOrganizationModel().removeOrganizationIfExists(oldOrganizationName);
    }
}
