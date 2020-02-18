package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.Activities.Utilities.PersonListSelectorListener;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class FindByOccupationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_occupation);
        Spinner occupationSelector = findViewById(R.id.findByOccupationOccupationSelector);
        ListView personList = findViewById(R.id.findByOccupationPersonList);

        ArrayAdapter<Occupation> occupationAdaptor = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Occupation.values());
        occupationSelector.setAdapter(occupationAdaptor);

        occupationSelector.setOnItemSelectedListener(
                new PersonListSelectorListener<Occupation>(
                        this,
                        personList,
                        item -> new ArrayList<>(ApplicationModels.getPersonModel().findPeople(item))));
        personList.setOnItemClickListener(new PersonSelectorListener(this, ViewPersonActivity.class));
    }
}
