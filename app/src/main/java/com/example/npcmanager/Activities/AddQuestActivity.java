package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.PersonModel;
import com.example.npcmanager.R;

import java.util.Optional;

public class AddQuestActivity extends AppCompatActivity {

    private Optional<String> existingQuestName;

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

        existingQuestName = getQuestNameMaybe();
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());
    }

    private void populateInputs() {
        if (existingQuestName.isPresent()) {
            Quest quest = ApplicationModels.getQuestModel().findFirstQuest(existingQuestName.get());
            populateInputs(
                    quest.getQuestName(),
                    Optional.of(quest.getQuestGiver()),
                    quest.getReturnLocation(),
                    quest.getDetails());
        } else {
            populateInputs(
                    "",
                    Optional.empty(),
                    ApplicationModels.getLocationModel()
                            .getLocationMaybe(Constants.NpcConstants.NONE).get(),
                    ""
            );
        }
    }

    private void populateInputs(
            String name,
            Optional<Person> questGiverMaybe,
            Location returnLocation,
            String details) {

        nameTextInput.setText(name);
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getAllLocations());
        locationSpinner.setAdapter(locationAdaptor);
        locationSpinner.setSelection(locationAdaptor.getPosition(
                returnLocation));

        PersonModel personModel = ApplicationModels.getPersonModel();
        ArrayAdapter<Person> personAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, personModel.getAllPeople());
        personSpinner.setAdapter(personAdaptor);
        questGiverMaybe.ifPresent(
                person -> personSpinner.setSelection(personAdaptor.getPosition(person)));
        detailsTextInput.setText(details);

    }

    private Optional<String> getQuestNameMaybe() {
        return Optional.ofNullable(getIntent().getExtras())
                .flatMap(extras -> Optional.ofNullable(extras.get("name")))
                .map(name -> (String) name);
    }

    private void createQuest() {
        existingQuestName.ifPresent(
                name -> ApplicationModels.getQuestModel().removeQuestIfExists(name));
        ApplicationModels.getQuestModel()
                .addQuest( new Quest(
                        nameTextInput.getText().toString(),
                        (Person)personSpinner.getSelectedItem(),
                        (Location)locationSpinner.getSelectedItem(),
                        detailsTextInput.getText().toString()));
    }

    private void saveButtonClicked() {
        createQuest();
        ActivityUtilities.loadMainActivity(this);
    }

    private void deleteButtonClicked() {
        existingQuestName.ifPresent(
                name -> ApplicationModels.getQuestModel().removeQuestIfExists(name));
        ActivityUtilities.loadMainActivity(this);
    }
}
