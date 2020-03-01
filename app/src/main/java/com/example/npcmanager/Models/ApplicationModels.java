package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.DataStructures.Race;

import java.util.List;

public class ApplicationModels {
    private String campaignName = "";
    private PersonModel personModel;
    private QuestModel questModel;
    private OrganizationModel organizationModel;
    private LocationModel locationModel;
    private RaceModel raceModel;
    private OccupationModel occupationModel;
    private GenderModel genderModel;

    public static ApplicationModels INSTANCE = new ApplicationModels();

    private ApplicationModels() {
        personModel = new PersonModel();
        questModel = new QuestModel();
        organizationModel = new OrganizationModel();
        locationModel = new LocationModel();
        raceModel = new RaceModel();
        occupationModel = new OccupationModel();
        genderModel = new GenderModel();
    }

    public static void resetModels() {
        INSTANCE = new ApplicationModels();
    }

    public static String getCampaignName() {
        return INSTANCE.campaignName;
    }
    public static PersonModel getPersonModel() {
        return INSTANCE.personModel;
    }
    public static QuestModel getQuestModel() {
        return INSTANCE.questModel;
    }
    public static OrganizationModel getOrganizationModel() {
        return INSTANCE.organizationModel;
    }
    public static LocationModel getLocationModel() {
        return INSTANCE.locationModel;
    }
    public static RaceModel getRaceModel() {
        return INSTANCE.raceModel;
    }
    public static OccupationModel getOccupationModel() {
        return INSTANCE.occupationModel;
    }
    public static GenderModel getGenderModel() {
        return INSTANCE.genderModel;
    }

    public static void setCampaignName(String campaignName) {
        INSTANCE.campaignName = campaignName;
    }
    public static void setPersonList(List<Person> people) {
        INSTANCE.personModel.replaceList(people);
    }
    public static void setQuestList(List<Quest> quests) {
        INSTANCE.questModel.replaceList(quests);
    }
    public static void setOrganizationList(List<Organization> organizations) {
        INSTANCE.organizationModel.replaceList(organizations);
    }
    public static void setLocationList(List<Location> locations) {
        INSTANCE.locationModel.replaceList(locations);
    }
    public static void setRaceList(List<Race> races) {
        INSTANCE.raceModel.replaceList(races);
    }
    public static void setOccupationList(List<Occupation> occupations) {
        INSTANCE.occupationModel.replaceList(occupations);
    }
    public static void setGenderList(List<Gender> genders) {
        INSTANCE.genderModel.replaceList(genders);
    }


}
