package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class AddQuestActivity extends HomeButtonActivity {

    private Optional<Quest> existingQuest;

    EditText nameTextInput;
    Spinner personSpinner;
    Spinner locationSpinner;
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
        personSpinner = findViewById(R.id.addQuestPersonSelector);
        locationSpinner = findViewById(R.id.addQuestLocationSelector);
        detailsTextInput = findViewById(R.id.addQuestDetailsInput);

        enableLocationText = findViewById(R.id.addQuestEnableLocationText);
        enablePersonText = findViewById(R.id.addQuestEnablePersonText);
        enableDetailsText = findViewById(R.id.addQuestEnableDetailsText);

        locationLabel = findViewById(R.id.addQuestLocationText);
        personLabel = findViewById(R.id.addQuestPersonText);
        detailsLabel = findViewById(R.id.addQuestDetailsText);

        Button saveButton = findViewById(R.id.addQuestSaveButton);
        Button deleteButton = findViewById(R.id.addQuestDeleteButton);

        existingQuest = ActivityUtilities.getQuestExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
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
        determineEnabledState(quest.getReturnLocation(), this::disableLocation, this::enableLocation);
        determineEnabledState(quest.getQuestGiver(), this::disablePerson, this::enablePerson);

        if (quest.getDetails().equals("")) {
            disableDetails();
        } else {
            enableDetails();
        }

        if (quest.isNone()) {
            nameTextInput.setText("");
            disableDetails();
        } else {
            nameTextInput.setText(quest.getQuestName());
            enableDetails();
        }
        setupLocationSelector(quest.getReturnLocation());
        setupPersonSelector(quest.getQuestGiver());
        detailsTextInput.setText(quest.getDetails());
    }

    private void determineEnabledState(BaseItem item, Runnable disable, Runnable enable) {
        if (item.isNone()) {
            disable.run();
        } else {
            enable.run();
        }
    }

    private void setupLocationSelector(Location location) {
        setupSelector(locationSpinner, ApplicationModels.getLocationModel().getList(), location);
    }

    private void setupPersonSelector(Person person) {
        setupSelector(personSpinner, ApplicationModels.getPersonModel().getList(), person);
    }

    private void setupSelector(Spinner selector, List<BaseItem> items, BaseItem item) {
        ArrayAdapter<BaseItem> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, items);
        selector.setAdapter(adapter);
        selector.setSelection(adapter.getPosition(item));
    }
    
    private void setupEnablers() {
        enableLocationText.setOnClickListener(v -> enableLocation());
        enablePersonText.setOnClickListener(v -> enablePerson());
        enableDetailsText.setOnClickListener(v -> enableDetails());
    }

    private void setupDisablers() {
        locationLabel.setOnLongClickListener(v -> disableLocation());
        locationSpinner.setOnLongClickListener(v -> disableLocation());

        personLabel.setOnLongClickListener(v -> disablePerson());
        personSpinner.setOnLongClickListener(v -> disablePerson());

        detailsLabel.setOnLongClickListener(v -> disableDetails());
        detailsTextInput.setOnLongClickListener(v -> disableDetails());
    }

    private void createQuest() {
        Quest newQuest = new Quest(
                nameTextInput.getText().toString(),
                getSelectedPerson(),
                getSelectedLocation(),
                detailsTextInput.getText().toString());

        if(existingQuest.isPresent()) {
            ApplicationModelUpdater.replaceQuest(existingQuest.get().getQuestName(), newQuest, this);
        } else {
            ApplicationModelUpdater.addQuest(newQuest, this);
        }
    }

    private Person getSelectedPerson() {
        return (Person) getSelectedItem(enablePersonText, personSpinner, Person::None);
    }

    private Location getSelectedLocation() {
        return (Location) getSelectedItem(enableLocationText, locationSpinner, Location::None);
    }

    private BaseItem getSelectedItem(
            View enableText, Spinner selector, Supplier<BaseItem> noneSupplier) {
        if (enableText.getVisibility() == View.INVISIBLE) {
            return (BaseItem) selector.getSelectedItem();
        } else {
            return noneSupplier.get();
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

    private void enableLocation() {
        enableItem(enableLocationText, locationLabel, locationSpinner);
    }

    private void enablePerson() {
        enableItem(enablePersonText, personLabel, personSpinner);
    }

    private void enableDetails() {
        enableItem(enableDetailsText, detailsLabel, detailsTextInput);
    }

    private boolean disableLocation() {
        return disableItem(enableLocationText, locationLabel, locationSpinner,
                () -> setupLocationSelector(Location.None()));
    }

    private boolean disablePerson() {
        return disableItem(enablePersonText, personLabel, personSpinner,
                () -> setupPersonSelector(Person.None()));
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

    private boolean disableItem(View enableText, View itemLabel, View itemSelector, Runnable runnable) {
        enableText.setVisibility(View.VISIBLE);
        itemLabel.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.INVISIBLE);
        runnable.run();
        return true;
    }
}
