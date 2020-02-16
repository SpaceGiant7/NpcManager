package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class ViewPersonActivity extends AppCompatActivity {

    private TextView nameTextInput;
    private TextView raceTextInput;
    private TextView genderTextInput;
    private TextView locationTextInput;
    private TextView occupationTextInput;
    private TextView organizationTextInput;
    private CheckBox deceasedCheckbox;
    private TextView descriptionTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        nameTextInput = findViewById(R.id.viewPersonNameText);
        raceTextInput = findViewById(R.id.viewPersonRaceText);
        genderTextInput = findViewById(R.id.viewPersonGenderText);
        locationTextInput = findViewById(R.id.viewPersonLocationText);
        occupationTextInput = findViewById(R.id.viewPersonOccupationText);
        organizationTextInput = findViewById(R.id.viewPersonOrganizationText);
        deceasedCheckbox = findViewById(R.id.viewPersonDeceasedCheckbox);
        descriptionTextInput = findViewById(R.id.viewPersonDescriptionText);
        String name = (String)getIntent().getExtras().get("name");
        setPerson(ApplicationModels.getPersonModel().findFirstPerson(name));
    }

    public void setPerson(Person person) {
        nameTextInput.setText(person.getName());
        raceTextInput.setText(person.getRace().getName());
        genderTextInput.setText(person.getGender().toString());
        locationTextInput.setText(person.getHome().getName());
        occupationTextInput.setText(person.getOccpation().toString());
        organizationTextInput.setText(person.getOrganization().getOrganizationName());
        deceasedCheckbox.setChecked(person.getIsDeceased());
        descriptionTextInput.setText(person.getDescription());
    }
}
