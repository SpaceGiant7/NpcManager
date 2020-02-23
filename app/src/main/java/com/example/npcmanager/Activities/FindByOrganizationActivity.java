package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.Optional;

public class FindByOrganizationActivity extends AppCompatActivity {

    Spinner organizationSelector;
    ListView personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_organization);
        organizationSelector = findViewById(R.id.findByOrganizationOrganizationSelector);
        personList = findViewById(R.id.findByOrganizationPersonList);

        setupSelector();
        setOnClickListeners();
    }

    private void setupSelector() {
        Optional<String> organizationMaybe = ActivityUtilities.getNameExtraMaybe(getIntent());
        ArrayAdapter<Organization> organizationAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getOrganizationModel().getAllOrganizations());

        organizationSelector.setAdapter(organizationAdapter);
        organizationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Organization>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));

        organizationMaybe.flatMap(name -> ApplicationModels.getOrganizationModel().findFirstOrganizationMaybe(name))
                .ifPresent( organization -> organizationSelector.setSelection(
                        organizationAdapter.getPosition(organization)));
    }

    private void setOnClickListeners() {
        personList.setOnItemClickListener(new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
