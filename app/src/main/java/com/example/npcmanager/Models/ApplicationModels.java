package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

import java.util.List;

public class ApplicationModels {
    private OrganizationModel organizationModel;
    private PersonModel personModel;
    private LocationModel locationModel;
    private QuestModel questModel;

    public static ApplicationModels INSTANCE = new ApplicationModels();

    private ApplicationModels() {
        organizationModel = new OrganizationModel();
        personModel = new PersonModel();
        locationModel = new LocationModel();
        questModel = new QuestModel();
    }

    public static void loadModels(ApplicationModels model) {
        setOrganizationModel(model.organizationModel);
        setPersonModel(model.personModel);
        setLocationModel(model.locationModel);
        setQuestModel(model.questModel);
    }

    public static OrganizationModel getOrganizationModel() {
        return INSTANCE.organizationModel;
    }

    public static void setOrganizationModel(OrganizationModel organizationModel) {
        INSTANCE.organizationModel = organizationModel;
    }

    public static void setOrganizationList(List<Organization> organizations) {
        INSTANCE.organizationModel.replaceList(organizations);
    }

    public static PersonModel getPersonModel() {
        return INSTANCE.personModel;
    }

    public static void setPersonModel(PersonModel personModel) {
        INSTANCE.personModel = personModel;
    }

    public static void setPersonList(List<Person> people) {
        INSTANCE.personModel.replaceList(people);
    }

    public static LocationModel getLocationModel() {
        return INSTANCE.locationModel;
    }

    public static void setLocationModel(LocationModel locationModel) {
        INSTANCE.locationModel = locationModel;
    }

    public static void setLocationList(List<Location> locations) {
        INSTANCE.locationModel.replaceList(locations);
    }

    public static QuestModel getQuestModel() {
        return INSTANCE.questModel;
    }

    public static void setQuestModel(QuestModel questModel) {
        INSTANCE.questModel = questModel;
    }

    public static void setQuestList(List<Quest> quests) {
        INSTANCE.questModel.replaceList(quests);
    }


}
