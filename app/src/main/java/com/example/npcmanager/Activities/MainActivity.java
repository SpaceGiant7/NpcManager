package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText campaingNameField = findViewById(R.id.campaignNameTextField);
        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton fileButton = findViewById(R.id.fileButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);
        Button findByNameButton = findViewById(R.id.findByNameButton);
        Button findByLocationButton = findViewById(R.id.findByLocationButton);
        Button findByOccupationButton = findViewById(R.id.findByOccupationButton);
        Button findByOrganizationButton = findViewById(R.id.findByOrganizationButton);
        Button findByRaceButton = findViewById(R.id.findByRaceButton);
        Button addPersonButton = findViewById(R.id.addPersonButton);
        Button addLocationButton = findViewById(R.id.addLocationButton);
        Button addOrganizationButton = findViewById(R.id.addOrganizationButton);
        Button addQuestButton = findViewById(R.id.addQuestButton);
        final LinearLayout fileMenu = findViewById(R.id.fileMenu);
        final LinearLayout addMenu = findViewById(R.id.addMenu);

        fileButton.setOnClickListener(getDiableMenuListener(fileMenu));
        addButton.setOnClickListener(getDiableMenuListener(addMenu));

        saveButton.setOnClickListener(
                v -> CampaignReaderWriter.save(campaingNameField.getText().toString(), this));
        loadButton.setOnClickListener(
                v -> CampaignReaderWriter.load(campaingNameField.getText().toString(), this));

        addPersonButton.setOnClickListener(v -> setActivityChange(AddPersonActivity.class));
        addLocationButton.setOnClickListener(v -> setActivityChange(AddLocationActivity.class));
        addOrganizationButton.setOnClickListener(v -> setActivityChange(AddOrganizationActivity.class));
        addQuestButton.setOnClickListener(v -> setActivityChange(AddQuestActivity.class));

        findByNameButton.setOnClickListener(v -> setActivityChange(FindByNameActivity.class));
        findByLocationButton.setOnClickListener(v -> setActivityChange(FindByLocationActivity.class));
        findByOccupationButton.setOnClickListener(v -> setActivityChange(FindByOccupationActivity.class));
        findByOrganizationButton.setOnClickListener(v -> setActivityChange(FindByOrganizationActivity.class));
        findByRaceButton.setOnClickListener(v -> setActivityChange(FindByRaceActivity.class));
    }

    private View.OnClickListener getDiableMenuListener(final LinearLayout menu){
        return view -> {
            if (menu.getVisibility() == View.VISIBLE){
                menu.setVisibility(View.INVISIBLE);
            } else{
                menu.setVisibility(View.VISIBLE);
            }
        };
    }

    private void setActivityChange(Class newActivityClass) {
        startActivity(new Intent( MainActivity.this, newActivityClass));
    }


}
