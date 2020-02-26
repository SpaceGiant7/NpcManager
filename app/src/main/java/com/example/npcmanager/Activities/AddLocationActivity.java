package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.R;

public class AddLocationActivity extends AppCompatActivity {

    private EditText locationNameText;
    private EditText locationDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        locationNameText = findViewById(R.id.addLocationNameInput);
        locationDescriptionText = findViewById(R.id.addLocationDescriptionInput);
        Button createButton = findViewById(R.id.addLocationCreate);

        createButton.setOnClickListener(v -> createLocation());
    }

    private void createLocation() {
        ApplicationModelUpdater.addLocation(
                Location.of(
                        locationNameText.getText().toString(),
                        locationDescriptionText.getText().toString()),
                this);
        ActivityUtilities.loadMainActivity(this);
    }
}
