package com.example.npcmanager.Activities.Utilities;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.NewMainActivity;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Race;

import java.io.Serializable;
import java.util.Optional;

public class ActivityUtilities {
    private static final String nameKey = "name";
    public static final String organizationKey = "organization";
    public static final String locationKey = "location";
    public static final String raceKey = "race";
    public static final String occupationKey = "occupation";
    public static final String genderKey = "gender";

    public static void loadMainActivity(AppCompatActivity fromActivity) {
        Intent intent = new Intent(fromActivity, NewMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithNameExtra(
            AppCompatActivity fromActivity, Class cls, CharSequence name) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(nameKey, name);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithExtra(
            AppCompatActivity fromActivity, Class cls, Serializable val, String key) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(key, val);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithExtra(
            Activity fromActivity, Class cls, Serializable val, String key) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(key, val);
        fromActivity.startActivity(intent);
    }

    public static String getNameExtra(Intent intent) {
        return (String) intent.getExtras().get(nameKey);
    }

    private static Optional<Serializable> getSerializableExtraMaybe(Intent intent, String key) {
        return Optional.ofNullable(intent.getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.getSerializable(key)));
    }

    public static Optional<Location> getLocationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, locationKey)
                .map(s -> (Location) s);
    }

    public static Optional<Organization> getOrganizationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, organizationKey)
                .map(s -> (Organization) s);
    }

    public static Optional<Race> getRaceExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, raceKey)
                .map(s -> (Race) s);
    }

    public static Optional<Occupation> getOccupationExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, occupationKey)
                .map(s -> (Occupation) s);
    }

    public static Optional<Gender> getGendereExtraMaybe(Intent intent) {
        return getSerializableExtraMaybe(intent, genderKey)
                .map(s -> (Gender) s);
    }


    public static Optional<String> getNameExtraMaybe(Intent intent) {
        return Optional.ofNullable(intent.getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get(nameKey)))
                .map(name -> (String) name);
    }
}


