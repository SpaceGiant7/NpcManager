package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.LocationModel;
import com.example.npcmanager.R;

public class AddOrganizationActivity extends AppCompatActivity {

    private EditText nameText;
    private Spinner locationSelector;
    private EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organization);
        nameText = findViewById(R.id.addOrganizationNameInput);
        locationSelector = findViewById(R.id.addOrganizationLocationSelector);
        descriptionText = findViewById(R.id.addOrganizationDescriptionInput);
        Button createButton = findViewById(R.id.addOrganizationCreate);

        LocationModel locationModel = ApplicationModels.getLocationModel();
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationModel.getAllItems());
        locationSelector.setAdapter(locationAdaptor);
        locationSelector.setSelection(locationAdaptor.getPosition(
                Location.None()));

        createButton.setOnClickListener(v -> addOrganization());
    }

    private void addOrganization() {
        ApplicationModelUpdater.addOrganization(
                new Organization(
                        nameText.getText().toString(),
                        (Location) locationSelector.getSelectedItem(),
                        descriptionText.getText().toString()));
    }
}
