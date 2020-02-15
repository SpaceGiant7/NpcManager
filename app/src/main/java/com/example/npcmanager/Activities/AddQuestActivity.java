package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.LocationModel;
import com.example.npcmanager.Models.PersonModel;
import com.example.npcmanager.R;

public class AddQuestActivity extends AppCompatActivity {

    EditText nameTextInput;
    Spinner personSpinner;
    Spinner locationSpinner;
    EditText descriptionTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);
        nameTextInput = findViewById(R.id.addQuestNameInput);
        personSpinner = findViewById(R.id.addQuestPersonSelector);
        locationSpinner = findViewById(R.id.addQuestLocationSelector);
        descriptionTextInput = findViewById(R.id.addQuestDescriptionInput);
        Button createButton = findViewById(R.id.addQuestCreate);

        LocationModel locationModel = ApplicationModels.getLocationModel();
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationModel.getAllLocations());
        locationSpinner.setAdapter(locationAdaptor);
        locationSpinner.setSelection(locationAdaptor.getPosition(
                locationModel.getLocation(Constants.NpcConstants.NONE).get()));

        PersonModel personModel = ApplicationModels.getPersonModel();
        ArrayAdapter<Person> personAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, personModel.getAllPeople());
        personSpinner.setAdapter(personAdaptor);
//        personSpinner.setSelection(personAdaptor.getPosition(
//                personModel.findFirstPerson(Constants.NpcConstants.NONE)));


        createButton.setOnClickListener(v -> createQuest());
    }

    private void createQuest() {
        ApplicationModels.getQuestModel()
                .addQuest( new Quest(
                        nameTextInput.getText().toString(),
                        (Person)personSpinner.getSelectedItem(),
                        (Location)locationSpinner.getSelectedItem(),
                        descriptionTextInput.getText().toString()));
    }
}
