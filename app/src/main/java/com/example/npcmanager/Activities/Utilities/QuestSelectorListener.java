package com.example.npcmanager.Activities.Utilities;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.DataStructures.Quest;

public class QuestSelectorListener implements AdapterView.OnItemClickListener {

    private AppCompatActivity currentActivity;
    private Class<?> activityClassToOpen;

    public QuestSelectorListener(AppCompatActivity currentActivity, Class<?> activityClassToOpen) {
        this.currentActivity = currentActivity;
        this.activityClassToOpen = activityClassToOpen;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Quest quest = (Quest) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent( currentActivity, activityClassToOpen);
        intent.putExtra("name", quest.getQuestName());
        currentActivity.startActivity(intent);
    }
}

