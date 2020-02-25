package com.example.npcmanager.Activities.FindBy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.PersonSelectorListener;
import com.example.npcmanager.Activities.ViewPersonActivity;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class FindByNameActivity extends AppCompatActivity {

    TextView nameInput;
    Button searchButton;
    ListView personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_name);
        nameInput = findViewById(R.id.findByNameNameInput);
        searchButton = findViewById(R.id.findByNameSearchButton);
        personList = findViewById(R.id.findByNamePersonList);
        searchButton.setOnClickListener(l -> fillPersonList());
        personList.setOnItemClickListener(
                new PersonSelectorListener(this, ViewPersonActivity.class));
    }

    private void fillPersonList() {
        ArrayAdapter<Person> nameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<>(ApplicationModels.getPersonModel()
                        .findPeople(nameInput.getText().toString())));
        personList.setAdapter(nameAdapter);
    }


}
