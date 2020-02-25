package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class ViewQuestActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView questGiverTextView;
    private TextView locationTextView;
    private TextView detailsTextView;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quest);
        nameTextView = findViewById(R.id.viewQuestNameText);
        questGiverTextView = findViewById(R.id.viewQuestPersonText);
        locationTextView = findViewById(R.id.viewQuestLocationText);
        detailsTextView = findViewById(R.id.viewQuestDetailsText);
        editButton = findViewById(R.id.viewQuestEditButton);

        String name = ActivityUtilities.getNameExtra(getIntent());
        setQuest(ApplicationModels.getQuestModel().getQuest(name));

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        editButton.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithNameExtra(
                        this, AddQuestActivity.class, nameTextView.getText()));
        questGiverTextView.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithNameExtra(
                        this, ViewPersonActivity.class, questGiverTextView.getText()));
        locationTextView.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithNameExtra(
                        this, FindByLocationActivity.class, locationTextView.getText()));
    }

    public void setQuest(Quest quest) {
        nameTextView.setText(quest.getQuestName());
        questGiverTextView.setText(quest.getQuestGiver().getName());
        locationTextView.setText(quest.getReturnLocation().getName());
        detailsTextView.setText(quest.getDetails());

    }
}
