package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
    }
}
