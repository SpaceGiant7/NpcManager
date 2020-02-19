package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.R;

public class CreateCampaignActivity extends AppCompatActivity {

    EditText campaignName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);
        campaignName = findViewById(R.id.createCampaignNameInput);
        Button embarkButton = findViewById(R.id.createCampaignEmbarkButton);

        embarkButton.setOnClickListener(v -> createCampaign());
    }

    private void createCampaign() {
        if (campaignName.getText().length() > 0) {
            CampaignReaderWriter.save(campaignName.getText().toString(), this);
        }
        ActivityUtilities.loadMainActivity(this);
    }
}
