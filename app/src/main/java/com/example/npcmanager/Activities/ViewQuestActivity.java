package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.ViewItemRecyclerAdapter;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.DataStructures.ViewItem;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ViewQuestActivity extends HomeButtonActivity {


    RecyclerView recyclerView;
    ViewItemRecyclerAdapter adapter;
    List<ViewItem> viewItems = new ArrayList<>();
    private TextView nameTextView;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quest);
        nameTextView = findViewById(R.id.viewQuestNameText);
        recyclerView = findViewById(R.id.viewQuestRecyclerView);
        editButton = findViewById(R.id.viewQuestEditButton);

        Quest quest = ActivityUtilities.getQuestExtraMaybe(getIntent()).get();

        setQuest(quest);

        setOnClickListeners();
    }

    public void setQuest(Quest quest) {
        nameTextView.setText(quest.getQuestName());
        setupRecyclerView(quest);

    }

    private void setupRecyclerView(Quest quest) {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createViewItems(quest);
        adapter = new ViewItemRecyclerAdapter(viewItems);
        recyclerView.setAdapter(adapter);
    }

    private List<ViewItem> createViewItems(Quest quest) {
        maybeAddViewItem(
                quest.getQuestGiver(),
                "Quest Giver",
                () -> ActivityUtilities.loadActivityWithPersonExtra(
                        this,
                        ViewPersonActivity.class,
                        quest.getQuestGiver().getIdentifier()));
        maybeAddViewItem(
                quest.getReturnLocation(),
                "Return Location",
                () -> ActivityUtilities.loadActivityWithLocationExtra(
                        this,
                        FindByLocationActivity.class,
                        quest.getReturnLocation().getIdentifier()));

        if (!quest.getDetails().equals("")) {
            viewItems.add(new ViewItem("Details", quest.getDetails()));
        }

        return viewItems;
    }

    private void maybeAddViewItem(
            BaseItem item, String label, Runnable runnable) {
        if (!item.isNone()) {
            viewItems.add(new ViewItem(label, item.getIdentifier(), runnable));
        }

    }

    private void setOnClickListeners() {
        editButton.setOnClickListener(
                v -> ActivityUtilities.loadActivityWithQuestExtra(
                        this,
                        AddQuestActivity.class,
                        nameTextView.getText().toString()));
    }


}
