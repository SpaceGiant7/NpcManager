package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.List;
import java.util.Optional;

public class AddPersonActivity extends HomeButtonActivity {

    private Optional<Person> existingPerson;

    private TextView nameTextInput;
    private Spinner raceSelector;
    private Spinner genderSelector;
    private Spinner homeSelector;
    private Spinner occupationSelector;
    private Spinner organizationSelector;
    private CheckBox deceasedCheckBox;
    private TextView descriptionTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        nameTextInput = findViewById(R.id.addPersonNameTextInput);
        raceSelector = findViewById(R.id.addPersonRaceSelector);
        genderSelector = findViewById(R.id.addPersonGenderSelector);
        homeSelector = findViewById(R.id.addPersonHomeSelector);
        occupationSelector = findViewById(R.id.addPersonOccupationSelector);
        organizationSelector = findViewById(R.id.addPersonOrganizationSelector);
        deceasedCheckBox = findViewById(R.id.addPersonDeceasedCheckBox);
        descriptionTextInput = findViewById(R.id.addPersonDescriptionTextInput);
        Button saveButton = findViewById(R.id.addPersonSaveButton);
        Button deleteButton = findViewById(R.id.addPersonDeleteButton);

        existingPerson = ActivityUtilities.getPersonExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
    }

    private void populateInputs() {
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            populateInputs(
                    person.getName(),
                    person.getRace(),
                    person.getGender(),
                    person.getHome(),
                    person.getOccpation(),
                    person.getOrganization(),
                    person.getIsDeceased(),
                    person.getDescription());
        } else {
            populateInputs(
                    "",
                    Race.None(),
                    Gender.None(),
                    Location.None(),
                    Occupation.None(),
                    Organization.None(),
                    false,
                    "");
        }
    }

    private void populateInputs(
            String name,
            Race race,
            Gender gender,
            Location location,
            Occupation occupation,
            Organization organization,
            boolean deceased,
            String description) {
        nameTextInput.setText(name);
        setupSelector(raceSelector,
                ApplicationModels.getRaceModel().getListWithNone(), race);
        setupSelector(genderSelector,
                ApplicationModels.getGenderModel().getListWithNone(), gender);
        setupSelector(homeSelector,
                ApplicationModels.getLocationModel().getListWithNone(), location);
        setupSelector(occupationSelector,
                ApplicationModels.getOccupationModel().getListWithNone(), occupation);
        setupSelector(organizationSelector,
                ApplicationModels.getOrganizationModel().getListWithNone(), organization);
        deceasedCheckBox.setChecked(deceased);
        descriptionTextInput.setText(description);
    }

    private void setupSelector(Spinner selector, List<BaseItem> items, BaseItem item) {
        ArrayAdapter<BaseItem> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, items);
        selector.setAdapter(adapter);
        selector.setSelection(adapter.getPosition(item));
    }

    private void createPerson() {
        Person newPerson = new Person(
                nameTextInput.getText().toString(),
                (Race)raceSelector.getSelectedItem(),
                (Gender)genderSelector.getSelectedItem(),
                (Location)homeSelector.getSelectedItem(),
                (Occupation)occupationSelector.getSelectedItem(),
                (Organization)organizationSelector.getSelectedItem(),
                deceasedCheckBox.isChecked(),
                descriptionTextInput.getText().toString());

        if( existingPerson.isPresent()) {
            ApplicationModelUpdater.replacePerson(existingPerson.get().getName(), newPerson, this);
        } else {
            ApplicationModelUpdater.addPerson(newPerson, this);
        }
    }

    private void saveButtonClicked() {
        createPerson();
        ActivityUtilities.loadMainActivity(this);
    }

    private void deleteButtonClicked() {
        existingPerson.ifPresent(
                person -> ApplicationModelUpdater.removePerson(person.getName(), this));
        ActivityUtilities.loadMainActivity(this);
    }
}
