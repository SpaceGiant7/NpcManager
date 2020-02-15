package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class AddLocationActivity extends AppCompatActivity {

    private EditText locationNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        locationNameText = findViewById(R.id.addLocationLocationName);
        Button createButton = findViewById(R.id.addLocationCreate);

        createButton.setOnClickListener(v -> createLocation());
    }

    private void createLocation() {
        ApplicationModels
                .getLocationModel()
                .addLocation(Location.of(locationNameText.getText().toString()));
    }
}
