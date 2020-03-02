package com.example.npcmanager.Activities.FindBy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.npcmanager.Activities.HomeButtonActivity;
import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.Activities.ViewPersonActivity;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.R;

import java.util.List;

public abstract class FindByActivity extends HomeButtonActivity {

    TextView titleText;
    TextView identifierText;
    ListView personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_location);
        titleText = findViewById(R.id.findByLocationTitle);
        identifierText = findViewById(R.id.findByLocationLocationText);
        personList = findViewById(R.id.findByLocationPersonList);

        identifierText.setText(deserializeInputs());
        titleText.setText("Find By " + getTitleString());

        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                getPersonList());
        personList.setAdapter(arrayAdapter);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }

    protected abstract String deserializeInputs();

    protected abstract List<Person> getPersonList();

    protected abstract String getTitleString();

}


