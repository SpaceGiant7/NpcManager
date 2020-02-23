package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class ViewLocationsActivity extends AppCompatActivity {


    private RecyclerView locationList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView name;
    private TextView description;
    private Button saveButton;
    private Button deleteButton;
    private Button findByButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);
        locationList = findViewById(R.id.viewLocationList);
        name = findViewById(R.id.viewLocationName);
        description = findViewById(R.id.viewLocationDescription);
        saveButton = findViewById(R.id.viewLocationSaveButton);
        deleteButton = findViewById(R.id.viewLocationDeleteButton);
        findByButton = findViewById(R.id.viewLocationFindByButton);

        locationList.setHasFixedSize(true); // Maybe not needed
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(
                ApplicationModels.getLocationModel().getList());

        locationList.setLayoutManager(layoutManager);
        locationList.setAdapter(adapter);


    }
}
