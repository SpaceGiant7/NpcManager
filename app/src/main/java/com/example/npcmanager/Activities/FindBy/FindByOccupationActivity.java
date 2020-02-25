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
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.Optional;

public class FindByOccupationActivity extends AppCompatActivity {

    Spinner occupationSelector;
    ListView personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_occupation);
        occupationSelector = findViewById(R.id.findByOccupationOccupationSelector);
        personList = findViewById(R.id.findByOccupationPersonList);

        setupSelector();
        setOnClickListeners();
    }

    private void setupSelector() {
        Optional<String> occupationMaybe = ActivityUtilities.getNameExtraMaybe(getIntent());
        ArrayAdapter<Occupation> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Occupation.values());

        occupationSelector.setAdapter(occupationAdaptor);
        occupationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Occupation>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));

        occupationMaybe.map(Occupation::fromName)
                .ifPresent(occupation -> occupationSelector.setSelection(
                        occupationAdaptor.getPosition(occupation)));
    }

    private void setOnClickListeners() {
        personList.setOnItemClickListener(new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
