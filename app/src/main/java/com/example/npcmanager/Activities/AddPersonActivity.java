package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.LocationModel;
import com.example.npcmanager.Models.OrganizationModel;
import com.example.npcmanager.R;

import java.util.Optional;

import Constants.NpcConstants;

public class AddPersonActivity extends AppCompatActivity {

    private Optional<String> existingPersonName;

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
        Button saveButton = findViewById(R.id.addPersonCreateButton);
        Button deleteButton = findViewById(R.id.addPersonDeleteButton);
        existingPersonName = getPersonNameMaybe();
        populateInputs(existingPersonName);

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
    }

    private void populateInputs(Optional<String> personNameMaybe) {
        if (personNameMaybe.isPresent()) {
            Person person = ApplicationModels.getPersonModel().findFirstPerson(personNameMaybe.get());
            populateInputs(
                    person.getName(),
                    person.getRace(),
                    person.getGender(),
                    person.getHome(),
                    person.getOccpation(),
                    person.getOrganization(),
                    person.getDescription());
        } else {
            populateInputs(
                    "",
                    Race.UNKNOWN,
                    Gender.UNKNOWN,
                    ApplicationModels.getLocationModel().getLocation(NpcConstants.NONE).get(),
                    Occupation.NONE,
                    ApplicationModels.getOrganizationModel().getOrganization(NpcConstants.NONE).get(),
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
            String description) {
        nameTextInput.setText(name);
        ArrayAdapter<Race> raceAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Race.values());
        raceSelector.setAdapter(raceAdaptor);
        raceSelector.setSelection(raceAdaptor.getPosition(race));

        ArrayAdapter<Gender> genderAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Gender.values());
        genderSelector.setAdapter(genderAdaptor);
        genderSelector.setSelection(genderAdaptor.getPosition(gender));

        LocationModel locationModel = ApplicationModels.getLocationModel();
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationModel.getAllLocations());
        homeSelector.setAdapter(locationAdaptor);
        homeSelector.setSelection(locationAdaptor.getPosition(location));

        ArrayAdapter<Occupation> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Occupation.values());
        occupationSelector.setAdapter(occupationAdaptor);
        occupationSelector.setSelection(occupationAdaptor.getPosition(occupation));

        OrganizationModel organizationModel = ApplicationModels.getOrganizationModel();
        ArrayAdapter<Organization> organizationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, organizationModel.getAllOrganizations());
        organizationSelector.setAdapter(organizationAdaptor);
        organizationSelector.setSelection(organizationAdaptor.getPosition(organization));
        descriptionTextInput.setText(description);
    }

    private Optional<String> getPersonNameMaybe() {
        return Optional.ofNullable(getIntent().getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get("name")))
                .map(name -> (String) name);
    }

    private void createPerson() {
        existingPersonName.ifPresent(
                name -> ApplicationModels.getPersonModel().removePersonIfExists(name));
        ApplicationModels.getPersonModel().addPerson(
                new Person(
                    nameTextInput.getText().toString(),
                        (Race)raceSelector.getSelectedItem(),
                        (Gender)genderSelector.getSelectedItem(),
                        (Location)homeSelector.getSelectedItem(),
                        (Occupation)occupationSelector.getSelectedItem(),
                        (Organization)organizationSelector.getSelectedItem(),
                    deceasedCheckBox.isSelected(),
                    descriptionTextInput.getText().toString()));
    }

    private void saveButtonClicked() {
        createPerson();
        loadMainActivity();
    }

    private void deleteButtonClicked() {
        existingPersonName.ifPresent(
                name -> ApplicationModels.getPersonModel().removePersonIfExists(name));
        loadMainActivity();
    }

    private void loadMainActivity() {
        Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
