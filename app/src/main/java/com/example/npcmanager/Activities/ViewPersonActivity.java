package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.FindBy.FindByGenderActivity;
import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.FindBy.FindByOccupationActivity;
import com.example.npcmanager.Activities.FindBy.FindByOrganizationActivity;
import com.example.npcmanager.Activities.FindBy.FindByRaceActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Person;
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
    private Button editButton;

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
        editButton = findViewById(R.id.viewPersonEditButton);

        setPerson(ActivityUtilities.getPersonExtraMaybe(getIntent()).get());

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        editButton.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithPersonExtra(
                        this,
                        AddPersonActivity.class,
                        nameTextInput.getText().toString()));
        raceTextInput.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithRaceExtra(
                        this,
                        FindByRaceActivity.class,
                        raceTextInput.getText().toString()));
        locationTextInput.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithLocationExtra(
                        this,
                        FindByLocationActivity.class,
                        locationTextInput.getText().toString()));
        occupationTextInput.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithOccupationExtra(
                        this,
                        FindByOccupationActivity.class,
                        occupationTextInput.getText().toString()));
        organizationTextInput.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithOrganizationExtra(
                        this,
                        FindByOrganizationActivity.class,
                        organizationTextInput.getText().toString()));
        genderTextInput.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithGenderExtra(
                        this,
                        FindByGenderActivity.class,
                        genderTextInput.getText().toString()));


    }

    public void setPerson(Person person) {
        nameTextInput.setText(person.getName());
        raceTextInput.setText(person.getRace().getName());
        genderTextInput.setText(person.getGender().toString());
        locationTextInput.setText(person.getHome().getName());
        occupationTextInput.setText(person.getOccpation().toString());
        organizationTextInput.setText(person.getOrganization().getName());
        deceasedCheckbox.setChecked(person.getIsDeceased());
        descriptionTextInput.setText(person.getDescription());
    }
}
