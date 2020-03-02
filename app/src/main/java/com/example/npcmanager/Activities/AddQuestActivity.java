package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.Optional;

public class AddQuestActivity extends HomeButtonActivity {

    private Optional<Quest> existingQuest;

    EditText nameTextInput;
    Spinner personSpinner;
    Spinner locationSpinner;
    EditText detailsTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);
        nameTextInput = findViewById(R.id.addQuestNameInput);
        personSpinner = findViewById(R.id.addQuestPersonSelector);
        locationSpinner = findViewById(R.id.addQuestLocationSelector);
        detailsTextInput = findViewById(R.id.addQuestDetailsInput);
        Button saveButton = findViewById(R.id.addQuestSaveButton);
        Button deleteButton = findViewById(R.id.addQuestDeleteButton);

        existingQuest = ActivityUtilities.getQuestExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
    }

    private void populateInputs() {
        if (existingQuest.isPresent()) {
            Quest quest = existingQuest.get();
            populateInputs(
                    quest.getQuestName(),
                    quest.getQuestGiver(),
                    quest.getReturnLocation(),
                    quest.getDetails());
        } else {
            populateInputs(
                    "",
                    Person.None(),
                    Location.None(),
                    ""
            );
        }
    }

    private void populateInputs(
            String name,
            Person questGiver,
            Location returnLocation,
            String details) {

        nameTextInput.setText(name);
        ArrayAdapter<BaseItem> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getListWithNone());
        locationSpinner.setAdapter(locationAdaptor);
        locationSpinner.setSelection(locationAdaptor.getPosition(
                returnLocation));

        ArrayAdapter<BaseItem> personAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getPersonModel().getListWithNone());
        personSpinner.setAdapter(personAdaptor);
        personSpinner.setSelection(personAdaptor.getPosition(questGiver));
        detailsTextInput.setText(details);

    }

    private Optional<String> getQuestNameMaybe() {
        return Optional.ofNullable(getIntent().getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get("name")))
                .map(name -> (String) name);
    }

    private void createQuest() {
        Quest newQuest = new Quest(
                nameTextInput.getText().toString(),
                (Person) personSpinner.getSelectedItem(),
                (Location) locationSpinner.getSelectedItem(),
                detailsTextInput.getText().toString());

        if(existingQuest.isPresent()) {
            ApplicationModelUpdater.replaceQuest(existingQuest.get().getQuestName(), newQuest, this);
        } else {
            ApplicationModelUpdater.addQuest(newQuest, this);
        }
    }

    private void saveButtonClicked() {
        createQuest();
        ActivityUtilities.loadMainActivity(this);
    }

    private void deleteButtonClicked() {
        existingQuest.ifPresent(
                quest -> ApplicationModelUpdater.removeQuest(quest.getQuestName(), this));
        ActivityUtilities.loadMainActivity(this);
    }
}
