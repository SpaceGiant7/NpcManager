package com.example.npcmanager.Activities.FindBy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.Activities.ViewPersonActivity;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.Optional;

public class FindByRaceActivity extends AppCompatActivity {

    Spinner raceSelector;
    ListView personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_race);
        raceSelector = findViewById(R.id.findByRaceRaceSelector);
        personList = findViewById(R.id.findByRacePersonList);

        setupSelector();
        setOnClickListeners();
    }

    private void setupSelector() {
        Optional<String> raceMaybe = ActivityUtilities.getNameExtraMaybe(getIntent());
        ArrayAdapter<Race> raceAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Race.values());

        raceSelector.setAdapter(raceAdaptor);
        raceSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Race>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));

        raceMaybe.map(Race::fromName)
                .ifPresent(race -> raceSelector.setSelection(
                        raceAdaptor.getPosition(race)));
    }

    private void setOnClickListeners() {
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
