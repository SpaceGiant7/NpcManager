package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Quest;
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

        setQuest(ActivityUtilities.getQuestExtraMaybe(getIntent()).get());

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        editButton.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithQuestExtra(
                        this,
                        AddQuestActivity.class,
                        nameTextView.getText().toString()));
        questGiverTextView.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithPersonExtra(
                        this,
                        ViewPersonActivity.class,
                        questGiverTextView.getText().toString()));
        locationTextView.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithLocationExtra(
                        this,
                        FindByLocationActivity.class,
                        locationTextView.getText().toString()));
    }

    public void setQuest(Quest quest) {
        nameTextView.setText(quest.getQuestName());
        questGiverTextView.setText(quest.getQuestGiver().getName());
        locationTextView.setText(quest.getReturnLocation().getName());
        detailsTextView.setText(quest.getDetails());

    }
}
