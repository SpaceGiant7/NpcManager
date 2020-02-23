package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.Optional;

public class FindByLocationActivity extends AppCompatActivity {

    Spinner locationSelector;
    ListView personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_location);
        locationSelector = findViewById(R.id.findByLocationLocationSelector);
        personList = findViewById(R.id.findByLocationPersonList);

        setupSelector();
        setOnClickListeners();
    }

    private void setupSelector() {
        Optional<String> locationMaybe = ActivityUtilities.getNameExtraMaybe(getIntent());
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getAllLocations());

        locationSelector.setAdapter(locationAdapter);
        locationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Location>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));

        locationMaybe.flatMap(name -> ApplicationModels.getLocationModel().findFirstLocationMaybe(name))
                .ifPresent(location -> locationSelector.setSelection(
                                locationAdapter.getPosition(location)));
    }

    private void setOnClickListeners() {
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }

}


