package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class FindByRaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_race);
        Spinner raceSelector = findViewById(R.id.findByRaceRaceSelector);
        ListView personList = findViewById(R.id.findByRacePersonList);

        ArrayAdapter<Race> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Race.values());
        raceSelector.setAdapter(occupationAdaptor);

        raceSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Race>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
