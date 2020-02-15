package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.DataStructures.Location;
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
    }

    private void fillPersonList() {
        ArrayAdapter<Person> nameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<>(ApplicationModels.getPersonModel()
                        .findPeople(nameInput.getText().toString())));
        personList.setAdapter(nameAdapter);

    }
}
