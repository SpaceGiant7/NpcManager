package com.example.npcmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView questList;
    Spinner campiagnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campiagnList = findViewById(R.id.mainCampaignSpinner);
        fillCampaignList();
        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton fileButton = findViewById(R.id.fileButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);
        Button findByNameButton = findViewById(R.id.mainFindByNameButton);
        Button findByLocationButton = findViewById(R.id.mainFindByLocationButton);
        Button findByOccupationButton = findViewById(R.id.mainFindByOccupationButton);
        Button findByOrganizationButton = findViewById(R.id.mainFindByOrganizationButton);
        Button findByRaceButton = findViewById(R.id.mainFindByRaceButton);
        Button addPersonButton = findViewById(R.id.addPersonButton);
        Button addLocationButton = findViewById(R.id.addLocationButton);
        Button addOrganizationButton = findViewById(R.id.addOrganizationButton);
        Button addQuestButton = findViewById(R.id.addQuestButton);
        questList = findViewById(R.id.mainQuestListView);
        final LinearLayout fileMenu = findViewById(R.id.fileMenu);
        final LinearLayout addMenu = findViewById(R.id.addMenu);

        fileButton.setOnClickListener(getDisableMenuListener(fileMenu));
        addButton.setOnClickListener(getDisableMenuListener(addMenu));

        saveButton.setOnClickListener(
                v -> CampaignReaderWriter.save((String)campiagnList.getSelectedItem(), this));
        loadButton.setOnClickListener(
                v -> {
                    CampaignReaderWriter.load((String)campiagnList.getSelectedItem(), this);
                    refreshActivity();
                });

        addPersonButton.setOnClickListener(v -> setActivityChange(AddPersonActivity.class));
        addLocationButton.setOnClickListener(v -> setActivityChange(AddLocationActivity.class));
        addOrganizationButton.setOnClickListener(v -> setActivityChange(AddOrganizationActivity.class));
        addQuestButton.setOnClickListener(v -> setActivityChange(AddQuestActivity.class));

        findByNameButton.setOnClickListener(v -> setActivityChange(FindByNameActivity.class));
        findByLocationButton.setOnClickListener(v -> setActivityChange(FindByLocationActivity.class));
        findByOccupationButton.setOnClickListener(v -> setActivityChange(FindByOccupationActivity.class));
        findByOrganizationButton.setOnClickListener(v -> setActivityChange(FindByOrganizationActivity.class));
        findByRaceButton.setOnClickListener(v -> setActivityChange(FindByRaceActivity.class));

        questList.setOnItemClickListener(new QuestSelectorListener(this, ViewQuestActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshActivity();

    }

    private void refreshActivity() {
        fillQuestList();
    }

    private void fillQuestList() {
        ArrayAdapter<Quest> nameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<>(ApplicationModels.getQuestModel().getAllQuests()));
        questList.setAdapter(nameAdapter);
    }

    private void fillCampaignList() {
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<>(CampaignReaderWriter.INSTANCE.getExistingCampaigns(this)));
        campiagnList.setAdapter(nameAdapter);

    }

    private View.OnClickListener getDisableMenuListener(final LinearLayout menu){
        return view -> {
            if (menu.getVisibility() == View.VISIBLE){
                menu.setVisibility(View.INVISIBLE);
            } else{
                menu.setVisibility(View.VISIBLE);
            }
        };
    }

    private void setActivityChange(Class newActivityClass) {
        startActivity(new Intent( MainActivity.this, newActivityClass));
    }

}
