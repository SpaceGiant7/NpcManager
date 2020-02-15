package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.LocationModel;
import com.example.npcmanager.R;

public class AddOrganizationActivity extends AppCompatActivity {

    private EditText organizationNameText;
    private Spinner organizationLocationSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organization);
        organizationNameText = findViewById(R.id.addOrganizationNameInput);
        organizationLocationSelector = findViewById(R.id.addOrganizationLocationSelector);
        Button createButton = findViewById(R.id.addOrganizationCreate);

        LocationModel locationModel = ApplicationModels.getLocationModel();
        ArrayAdapter<Location> locationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, locationModel.getAllLocations());
        organizationLocationSelector.setAdapter(locationAdaptor);
        organizationLocationSelector.setSelection(locationAdaptor.getPosition(
                locationModel.getLocation(Constants.NpcConstants.NONE).get()));

        createButton.setOnClickListener(v -> addOrganization());
    }

    private void addOrganization(){
        ApplicationModels.getOrganizationModel().addOrganization(
                new Organization(
                        organizationNameText.getText().toString(),
                        (Location)organizationLocationSelector.getSelectedItem()));
    }
}
