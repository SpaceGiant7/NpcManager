package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class FindByLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_location);
        Spinner locationSelector = findViewById(R.id.findByLocationLocationSelector);
        ListView personList = findViewById(R.id.findByLocationPersonList);

        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getAllLocations());
        locationSelector.setAdapter(locationAdapter);

        locationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Location>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }

}


