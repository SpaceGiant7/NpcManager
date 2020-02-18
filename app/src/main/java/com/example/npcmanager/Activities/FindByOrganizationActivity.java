package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class FindByOrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_organization);
        Spinner organizationSelector = findViewById(R.id.findByOrganizationOrganizationSelector);
        ListView personList = findViewById(R.id.findByOrganizationPersonList);

        ArrayAdapter<Organization> organizationAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                ApplicationModels.getOrganizationModel().getAllOrganizations());
        organizationSelector.setAdapter(organizationAdapter);

        organizationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Organization>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));
        personList.setOnItemClickListener(new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
