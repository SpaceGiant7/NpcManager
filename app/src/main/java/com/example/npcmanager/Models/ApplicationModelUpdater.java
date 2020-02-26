package com.example.npcmanager.Models;

import android.content.Context;

import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

public class ApplicationModelUpdater {

    private static void save(Context context) {
        CampaignReaderWriter.save(ApplicationModels.getCampaignName(), context);
    }

    public static void addPerson(Person person, Context context) {
        ApplicationModels.getPersonModel().addItem(person);
        save(context);
    }

    public static void removePerson(String personName, Context context) {
        replacePersonInModels(personName, Person.None());
        save(context);
    }

    public static void replacePerson(String oldPersonName, Person newPerson, Context context) {
        replacePersonInModels(oldPersonName, newPerson);
        addPerson(newPerson, context);
    }

    private static void replacePersonInModels(String oldPersonName, Person newPerson) {
        ApplicationModels.getQuestModel().replacePerson(oldPersonName, newPerson);
        ApplicationModels.getPersonModel().removeItemIfExists(oldPersonName);
    }

    public static void addQuest(Quest quest, Context context) {
        ApplicationModels.getQuestModel().addItem(quest);
        save(context);
    }

    public static void removeQuest(String questName, Context context) {
        ApplicationModels.getQuestModel().removeItemIfExists(questName);
        save(context);
    }

    public static void replaceQuest(String oldQuestName, Quest newQuest, Context context) {
        removeQuest(oldQuestName, context);
        addQuest(newQuest, context);
    }

    public static void addLocation(Location location, Context context) {
        ApplicationModels.getLocationModel().addItem(location);
        save(context);
    }

    public static void removeLocation(String locationName, Context context) {
        replaceLocationInModels(locationName, Location.None());
        save(context);
    }

    public static void replaceLocation(String oldLocationName, Location newLocation, Context context) {
        replaceLocationInModels(oldLocationName, newLocation);
        addLocation(newLocation, context);
    }

    private static void replaceLocationInModels(String oldLocationName, Location newLocation) {
        ApplicationModels.getPersonModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getQuestModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getOrganizationModel().replaceLocation(oldLocationName, newLocation);
        ApplicationModels.getLocationModel().removeItemIfExists(oldLocationName);
    }

    public static void addOrganization(Organization organization, Context context) {
        ApplicationModels.getOrganizationModel().addItem(organization);
        save(context);
    }

    public static void removeOrganization(String organizationName, Context context) {
        replaceOrganizationInModels(organizationName, Organization.None());
        save(context);
    }

    public static void replaceOrganization(
            String oldOrganizationName, Organization newOrganization, Context context) {
        replaceOrganizationInModels(oldOrganizationName, newOrganization);
        addOrganization(newOrganization, context);
    }

    private static void replaceOrganizationInModels(
            String oldOrganizationName, Organization newOrganization) {
        ApplicationModels.getPersonModel()
                .replaceOrganization(oldOrganizationName, newOrganization);
        ApplicationModels.getOrganizationModel().removeItemIfExists(oldOrganizationName);
    }
}
