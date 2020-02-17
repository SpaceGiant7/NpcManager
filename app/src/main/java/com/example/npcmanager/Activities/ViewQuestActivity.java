package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class ViewQuestActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView questGiverTextView;
    private TextView locationTextView;
    private TextView detailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quest);
        nameTextView = findViewById(R.id.viewQuestNameText);
        questGiverTextView = findViewById(R.id.viewQuestPersonText);
        locationTextView = findViewById(R.id.viewQuestLocationText);
        detailsTextView = findViewById(R.id.viewQuestDetailsText);
        String name = (String)getIntent().getExtras().get("name");
        setQuest(ApplicationModels.getQuestModel().findFirstQuest(name));
    }

    public void setQuest(Quest quest) {
        nameTextView.setText(quest.getQuestName());
        questGiverTextView.setText(quest.getQuestGiver().getName());
        locationTextView.setText(quest.getReturnLocation().getName());
        detailsTextView.setText(quest.getDetails());

    }
}
