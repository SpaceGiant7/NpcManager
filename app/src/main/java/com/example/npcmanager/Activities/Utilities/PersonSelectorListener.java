package com.example.npcmanager.Activities.Utilities;

import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.DataStructures.Person;

public class PersonSelectorListener implements AdapterView.OnItemClickListener {
    private AppCompatActivity currentActivity;
    private Class<?> activityClassToOpen;

    public PersonSelectorListener(AppCompatActivity currentActivity, Class<?> activityClassToOpen) {
        this.currentActivity = currentActivity;
        this.activityClassToOpen = activityClassToOpen;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Person person = (Person) adapterView.getAdapter().getItem(i);
        ActivityUtilities.loadActivityWithExtra(
                currentActivity, activityClassToOpen, person, ActivityUtilities.personKey);
    }
}

