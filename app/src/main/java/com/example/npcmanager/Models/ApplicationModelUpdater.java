package com.example.npcmanager.Models;

import android.content.Context;

import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.DataStructures.Race;

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

    public static void addRace(Race race, Context context) {
        ApplicationModels.getRaceModel().addItem(race);
        save(context);
    }

    public static void removeRace(String raceName, Context context) {
        replaceRaceInModels(raceName, Race.None());
        save(context);
    }

    public static void replaceRace(
            String oldRaceName, Race newRace, Context context) {
        replaceRaceInModels(oldRaceName, newRace);
        addRace(newRace, context);
    }

    private static void replaceRaceInModels(
            String oldRaceName, Race newRace) {
        ApplicationModels.getPersonModel()
                .replaceRace(oldRaceName, newRace);
        ApplicationModels.getRaceModel().removeItemIfExists(oldRaceName);
    }

    public static void addOccupation(Occupation occupation, Context context) {
        ApplicationModels.getOccupationModel().addItem(occupation);
        save(context);
    }

    public static void removeOccupation(String occupationName, Context context) {
        replaceOccupationInModels(occupationName, Occupation.None());
        save(context);
    }

    public static void replaceOccupation(
            String oldOccupationName, Occupation newOccupation, Context context) {
        replaceOccupationInModels(oldOccupationName, newOccupation);
        addOccupation(newOccupation, context);
    }

    private static void replaceOccupationInModels(
            String oldOccupationName, Occupation newOccupation) {
        ApplicationModels.getPersonModel()
                .replaceOccupation(oldOccupationName, newOccupation);
        ApplicationModels.getOccupationModel().removeItemIfExists(oldOccupationName);
    }

    public static void addGender(Gender gender, Context context) {
        ApplicationModels.getGenderModel().addItem(gender);
        save(context);
    }

    public static void removeGender(String genderName, Context context) {
        replaceGenderInModels(genderName, Gender.None());
        save(context);
    }

    public static void replaceGender(
            String oldGenderName, Gender newGender, Context context) {
        replaceGenderInModels(oldGenderName, newGender);
        addGender(newGender, context);
    }

    private static void replaceGenderInModels(
            String oldGenderName, Gender newGender) {
        ApplicationModels.getPersonModel()
                .replaceGender(oldGenderName, newGender);
        ApplicationModels.getGenderModel().removeItemIfExists(oldGenderName);
    }
}
