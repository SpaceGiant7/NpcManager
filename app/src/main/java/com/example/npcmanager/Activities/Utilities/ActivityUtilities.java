package com.example.npcmanager.Activities.Utilities;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.NewMainActivity;
import com.example.npcmanager.Activities.ViewCampaignsActivity;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.PersonTrait;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;

import java.io.Serializable;
import java.util.Optional;

public class ActivityUtilities {
    public static final String personKey = "person";
    public static final String questKey = "quest";
    public static final String organizationKey = "organization";
    public static final String locationKey = "location";
    public static final String raceKey = "race";
    public static final String occupationKey = "occupation";
    public static final String genderKey = "gender";
    private static final String nameKey = "name";
    private static final String traitKey = "trait";

    public static void loadActivity(Activity fromActivity, Class cls) {
        fromActivity.startActivity(new Intent(fromActivity, cls));
    }

    public static void loadMainActivity(AppCompatActivity fromActivity) {
        loadActivityWithoutBackAbility(fromActivity, NewMainActivity.class);
    }

    public static void loadCampaignSelectorActivity(Activity fromActivity) {
        loadActivityWithoutBackAbility(fromActivity, ViewCampaignsActivity.class);
    }

    private static void loadActivityWithoutBackAbility(Activity fromActivity, Class cls) {
        Intent intent = new Intent(fromActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithPersonExtra(
            Activity fromActivity, Class cls, String personName) {
        loadActivityWithExtra(
                fromActivity, cls, ApplicationModels.getPersonModel().getItem(personName), personKey);
    }

    public static void loadActivityWithQuestExtra(
            Activity fromActivity, Class cls, String questName) {
        loadActivityWithExtra(
                fromActivity, cls, ApplicationModels.getQuestModel().getItem(questName), questKey);
    }

    public static void loadActivityWithOrganizationExtra(
            Activity fromActivity, Class cls, String organizationName) {
        loadActivityWithExtra(
                fromActivity,
                cls,
                ApplicationModels.getOrganizationModel().getItem(organizationName),
                organizationKey);
    }

    public static void loadActivityWithLocationExtra(
            Activity fromActivity, Class cls, String locationName) {
        loadActivityWithExtra(
                fromActivity,
                cls,
                ApplicationModels.getLocationModel().getItem(locationName),
                locationKey);
    }

    public static void loadActivityWithRaceExtra(
            Activity fromActivity, Class cls, String raceName) {
        loadActivityWithExtra(
                fromActivity, cls, ApplicationModels.getRaceModel().getItem(raceName), raceKey);
    }

    public static void loadActivityWithOccupationExtra(
            Activity fromActivity, Class cls, String occupationName) {
        loadActivityWithExtra(
                fromActivity,
                cls,
                ApplicationModels.getOccupationModel().getItem(occupationName),
                occupationKey);
    }

    public static void loadActivityWithGenderExtra(
            Activity fromActivity, Class cls, String genderName) {
        loadActivityWithExtra(
                fromActivity,
                cls,
                ApplicationModels.getGenderModel().getItem(genderName),
                genderKey);
    }

    public static void loadActivityWithExtra(
            Activity fromActivity, Class cls, Serializable val, String key) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(key, val);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithExtraAndTrait(
            Activity fromActivity, Class cls, Serializable val, String key, PersonTrait trait) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(key, val);
        intent.putExtra(traitKey, trait);
        fromActivity.startActivity(intent);
    }

    private static Optional<Serializable> getSerializableExtraMaybe(Intent intent, String key) {
        return Optional.ofNullable(intent.getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.getSerializable(key)));
    }

    public static Optional<Person> getPersonExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, personKey)
                .map(s -> (Person) s);
    }

    public static Optional<Quest> getQuestExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, questKey)
                .map(s -> (Quest) s);
    }

    public static Optional<BaseItem> getLocationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, locationKey)
                .map(s -> (BaseItem) s);
    }

    public static Optional<BaseItem> getOrganizationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, organizationKey)
                .map(s -> (BaseItem) s);
    }

    public static Optional<BaseItem> getRaceExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, raceKey)
                .map(s -> (BaseItem) s);
    }

    public static Optional<BaseItem> getOccupationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, occupationKey)
                .map(s -> (BaseItem) s);
    }

    public static Optional<BaseItem> getGenderExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, genderKey)
                .map(s -> (BaseItem) s);
    }

    public static Optional<String> getNameExtraMaybe(Intent intent) {
        return Optional.ofNullable(intent.getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get(nameKey)))
                .map(name -> (String) name);
    }
}


