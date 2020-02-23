package com.example.npcmanager.Activities.Utilities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.MainActivity;

import java.util.Optional;

public class ActivityUtilities {
    private static String nameKey = "name";

    public static void loadMainActivity(AppCompatActivity fromActivity) {
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromActivity.startActivity(intent);
    }

    public static void loadMainActivityWithCampaignName(AppCompatActivity fromActivity, String name) {
        Intent intent = new Intent(fromActivity, MainActivity.class);
        intent.putExtra(nameKey, name);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromActivity.startActivity(intent);
    }

    public static void loadActivityWithNameExtra(
            AppCompatActivity fromActivity, Class cls, CharSequence name) {
        Intent intent = new Intent(fromActivity, cls);
        intent.putExtra(nameKey, name);
        fromActivity.startActivity(intent);
    }

    public static String getNameExtra(Intent intent) {
        return (String) intent.getExtras().get(nameKey);
    }

    public static Optional<String> getNameExtraMaybe(Intent intent) {
        return Optional.ofNullable(intent.getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get("name")))
                .map(name -> (String) name);
    }
}


