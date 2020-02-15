package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.ArrayList;

import Constants.NpcConstants;

public class AddPersonActivity extends AppCompatActivity {

    private TextView nameTextInput;
    private Spinner raceSelector;
    private Spinner genderSelector;
    private Spinner homeSelector;
    private Spinner occupationSelector;
    private Spinner organizationSelector;
    private CheckBox deceasedCheckBox;
    private TextView descriptionTextInput;
    private Button createButton;

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
        createButton = findViewById(R.id.addPersonCreateButton);

        populateSelectors();
        createButton.setOnClickListener(v -> createPerson());
    }

    private void populateSelectors() {
        ArrayAdapter<Race> raceAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Race.values());
        raceSelector.setAdapter(raceAdaptor);
        raceSelector.setSelection(raceAdaptor.getPosition(Race.UNKNOWN));

        ArrayAdapter<Gender> genderAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Gender.values());
        genderSelector.setAdapter(genderAdaptor);
        genderSelector.setSelection(genderAdaptor.getPosition(Gender.UNKNOWN));

        LocationModel locationModel = ApplicationModels.getLocationModel();
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationModel.getAllLocations());
        homeSelector.setAdapter(locationAdaptor);
        homeSelector.setSelection(locationAdaptor.getPosition(
                locationModel.getLocation(NpcConstants.NONE).get()));

        ArrayAdapter<Occupation> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Occupation.values());
        occupationSelector.setAdapter(occupationAdaptor);
        occupationSelector.setSelection(occupationAdaptor.getPosition(Occupation.NONE));

        OrganizationModel organizationModel = ApplicationModels.getOrganizationModel();
        ArrayAdapter<Organization> organizationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, organizationModel.getAllOrganizations());
        organizationSelector.setAdapter(organizationAdaptor);
        organizationSelector.setSelection(organizationAdaptor.getPosition(
                organizationModel.getOrganization(NpcConstants.NONE).get()));
    }

    private void createPerson() {
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
}
