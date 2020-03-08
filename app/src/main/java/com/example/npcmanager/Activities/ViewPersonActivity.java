package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.FindBy.FindByGenderActivity;
import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.FindBy.FindByOccupationActivity;
import com.example.npcmanager.Activities.FindBy.FindByOrganizationActivity;
import com.example.npcmanager.Activities.FindBy.FindByRaceActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapters.ViewItemRecyclerAdapter;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.ViewItem;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPersonActivity extends HomeButtonActivity {

    RecyclerView recyclerView;
    ViewItemRecyclerAdapter adapter;
    List<ViewItem> viewItems = new ArrayList<>();
    private TextView nameTextInput;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        recyclerView = findViewById(R.id.viewPersonRecyclerView);
        nameTextInput = findViewById(R.id.viewPersonNameText);
        editButton = findViewById(R.id.viewPersonEditButton);

        setPerson(ActivityUtilities.getPersonExtraMaybe(getIntent()).get());

        setOnClickListeners();
    }

    public void setPerson(Person person) {
        nameTextInput.setText(person.getName());
        setupRecyclerView(person);
    }

    private void setupRecyclerView(Person person) {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createViewItems(person);
        adapter = new ViewItemRecyclerAdapter(viewItems);
        recyclerView.setAdapter(adapter);
    }

    private List<ViewItem> createViewItems(Person person) {
        maybeAddViewItem(
                person.getRace(),
                "Race",
                () -> ActivityUtilities.loadActivityWithRaceExtra(
                        this,
                        FindByRaceActivity.class,
                        person.getRace().getIdentifier()));
        maybeAddViewItem(
                person.getGender(),
                "Gender",
                () -> ActivityUtilities.loadActivityWithGenderExtra(
                        this,
                        FindByGenderActivity.class,
                        person.getGender().getIdentifier()));
        maybeAddViewItem(
                person.getHome(),
                "Location",
                () -> ActivityUtilities.loadActivityWithLocationExtra(
                        this,
                        FindByLocationActivity.class,
                        person.getHome().getIdentifier()));
        maybeAddViewItem(
                person.getOccpation(),
                "Occupation",
                () -> ActivityUtilities.loadActivityWithOccupationExtra(
                        this,
                        FindByOccupationActivity.class,
                        person.getOccpation().getIdentifier()));
        maybeAddViewItem(
                person.getOrganization(),
                "Organization",
                () -> ActivityUtilities.loadActivityWithOrganizationExtra(
                        this,
                        FindByOrganizationActivity.class,
                        person.getOrganization().getIdentifier()));

        if(!person.getMortality().isNone()) {
            viewItems.add(new ViewItem("Mortality", person.getMortality().getIdentifier()));
        }

        if (!person.getDescription().equals("")) {
            viewItems.add(new ViewItem("Description", person.getDescription()));
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
                v -> ActivityUtilities.loadActivityWithPersonExtra(
                        this,
                        AddPersonActivity.class,
                        nameTextInput.getText().toString()));
    }


}
