package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.DeselectableSpinnerAdapter;
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
    DeselectableSpinnerAdapter personSelector;
    DeselectableSpinnerAdapter locationSelector;
    EditText detailsTextInput;

    TextView enableLocationText;
    TextView enablePersonText;
    TextView enableDetailsText;

    TextView locationLabel;
    TextView personLabel;
    TextView detailsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);
        nameTextInput = findViewById(R.id.addQuestNameInput);
        detailsTextInput = findViewById(R.id.addQuestDetailsInput);

        enableLocationText = findViewById(R.id.addQuestEnableLocationText);
        enablePersonText = findViewById(R.id.addQuestEnablePersonText);
        enableDetailsText = findViewById(R.id.addQuestEnableDetailsText);

        locationLabel = findViewById(R.id.addQuestLocationText);
        personLabel = findViewById(R.id.addQuestPersonText);
        detailsLabel = findViewById(R.id.addQuestDetailsText);

        ImageButton saveButton = findViewById(R.id.addQuestSaveButton);

        existingQuest = ActivityUtilities.getQuestExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        setupEnablers();
        setupDisablers();
    }

    private void populateInputs() {
        if (existingQuest.isPresent()) {
            populateInputs(existingQuest.get());
        } else {
            populateInputs(Quest.None());
        }
    }

    private void populateInputs(Quest quest) {
        if (quest.getDetails().equals("")) {
            disableDetails();
        } else {
            enableDetails();
        }
        if (quest.isNone()) {
            nameTextInput.setText("");
        } else {
            nameTextInput.setText(quest.getQuestName());
        }
        setupLocationSelector(quest.getReturnLocation());
        setupPersonSelector(quest.getQuestGiver());
        detailsTextInput.setText(quest.getDetails());

        determineEnabledState(quest.getReturnLocation(), this::disableLocation, this::enableLocation);
        determineEnabledState(quest.getQuestGiver(), this::disablePerson, this::enablePerson);
    }

    private void determineEnabledState(BaseItem item, Runnable disable, Runnable enable) {
        if (item.isNone()) {
            disable.run();
        } else {
            enable.run();
        }
    }

    private void setupLocationSelector(Location location) {
        locationSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.addQuestLocationSelector,
                ApplicationModels.getLocationModel().getList(),
                location,
                Location::None);
    }

    private void setupPersonSelector(Person person) {
        personSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.addQuestPersonSelector,
                ApplicationModels.getPersonModel().getList(),
                person,
                Person::None);
    }
    
    private void setupEnablers() {
        enableLocationText.setOnClickListener(v -> enableLocation());
        enablePersonText.setOnClickListener(v -> enablePerson());
        enableDetailsText.setOnClickListener(v -> enableDetails());
    }

    private void setupDisablers() {
        locationLabel.setOnLongClickListener(v -> disableLocation());
        locationSelector.setOnLongClickListener(v -> disableLocation());

        personLabel.setOnLongClickListener(v -> disablePerson());
        personSelector.setOnLongClickListener(v -> disablePerson());

        detailsLabel.setOnLongClickListener(v -> disableDetails());
        detailsTextInput.setOnLongClickListener(v -> disableDetails());
    }

    private void createQuest() {
        Quest newQuest = new Quest(
                nameTextInput.getText().toString(),
                (Person) personSelector.getSelectedItem(),
                (Location) locationSelector.getSelectedItem(),
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

    private void enableLocation() {
        enableItem(enableLocationText, locationLabel, locationSelector);
    }

    private void enablePerson() {
        enableItem(enablePersonText, personLabel, personSelector);
    }

    private void enableDetails() {
        enableItem(enableDetailsText, detailsLabel, detailsTextInput);
    }

    private boolean disableLocation() {
        return disableItem(enableLocationText, locationLabel, locationSelector);
    }

    private boolean disablePerson() {
        return disableItem(enablePersonText, personLabel, personSelector);
    }

    private boolean disableDetails() {
        return disableItem(enableDetailsText, detailsLabel, detailsTextInput,
                () -> detailsTextInput.setText(""));
    }

    private void enableItem(View enableText, View itemLabel, View itemSelector) {
        enableText.setVisibility(View.INVISIBLE);
        itemLabel.setVisibility(View.VISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private void enableItem(View enableText, View itemLabel, DeselectableSpinnerAdapter itemSelector) {
        enableText.setVisibility(View.INVISIBLE);
        itemLabel.setVisibility(View.VISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private boolean disableItem(View enableText, View itemLabel, View itemSelector, Runnable runnable) {
        enableText.setVisibility(View.VISIBLE);
        itemLabel.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.INVISIBLE);
        runnable.run();
        return true;
    }

    private boolean disableItem(View enableText, View itemLabel, DeselectableSpinnerAdapter itemSelector) {
        enableText.setVisibility(View.VISIBLE);
        itemLabel.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.INVISIBLE);
        itemSelector.setSelection(0);
        return true;
    }
}
