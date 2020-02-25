package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.LocationModel;
import com.example.npcmanager.Models.OrganizationModel;
import com.example.npcmanager.R;

import java.util.Optional;

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
        Button saveButton = findViewById(R.id.addPersonSaveButton);
        Button deleteButton = findViewById(R.id.addPersonDeleteButton);

        existingPersonName = ActivityUtilities.getNameExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
    }

    private void populateInputs() {
        if (existingPersonName.isPresent()) {
            Person person = ApplicationModels.getPersonModel().findFirstPerson(existingPersonName.get());
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
                    Race.UNKNOWN,
                    Gender.UNKNOWN,
                    Location.None(),
                    Occupation.NONE,
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
                this, android.R.layout.simple_spinner_item, locationModel.getAllItems());
        homeSelector.setAdapter(locationAdaptor);
        homeSelector.setSelection(locationAdaptor.getPosition(location));

        ArrayAdapter<Occupation> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Occupation.values());
        occupationSelector.setAdapter(occupationAdaptor);
        occupationSelector.setSelection(occupationAdaptor.getPosition(occupation));

        OrganizationModel organizationModel = ApplicationModels.getOrganizationModel();
        ArrayAdapter<Organization> organizationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, organizationModel.getAllItems());
        organizationSelector.setAdapter(organizationAdaptor);
        organizationSelector.setSelection(organizationAdaptor.getPosition(organization));
        deceasedCheckBox.setChecked(deceased);
        descriptionTextInput.setText(description);
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

        if( existingPersonName.isPresent()) {
            ApplicationModelUpdater.replacePerson(existingPersonName.get(), newPerson);
        } else {
            ApplicationModelUpdater.addPerson(newPerson);
        }
    }

    private void saveButtonClicked() {
        createPerson();
        ActivityUtilities.loadMainActivity(this);
    }

    private void deleteButtonClicked() {
        existingPersonName.ifPresent(
                ApplicationModelUpdater::removePerson);
        ActivityUtilities.loadMainActivity(this);
    }
}
