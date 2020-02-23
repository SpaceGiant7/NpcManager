package com.example.npcmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.Utilities.QuestSelectorListener;
import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton addButton;
    ImageButton fileButton;
    Button saveButton;
    Button loadButton;
    Button newCampaignButton;
    Button deleteCampaignButton;
    Button findByNameButton;
    Button findByLocationButton;
    Button findByOccupationButton;
    Button findByOrganizationButton;
    Button findByRaceButton;
    Button addPersonButton;
    Button addLocationButton;
    Button addOrganizationButton;
    Button addQuestButton;
    ListView questList;
    Spinner campiagnList;
    LinearLayout fileMenu;
    LinearLayout addMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getElementReferences();
        fillCampaignList();
        defineMenuListeners();
        defineFileListeners();
        defineAddObjectListeners();
        defineSerachListeners();

        questList.setOnItemClickListener(new QuestSelectorListener(this, ViewQuestActivity.class));
    }

    private void getElementReferences() {
        campiagnList = findViewById(R.id.mainCampaignSpinner);
        addButton = findViewById(R.id.addButton);
        fileButton = findViewById(R.id.fileButton);
        saveButton = findViewById(R.id.mainSaveButton);
        loadButton = findViewById(R.id.mainLoadButton);
        newCampaignButton = findViewById(R.id.mainNewCampaignButon);
        deleteCampaignButton = findViewById(R.id.mainDeleteCampaignButton);
        findByNameButton = findViewById(R.id.mainFindByNameButton);
        findByLocationButton = findViewById(R.id.mainFindByLocationButton);
        findByOccupationButton = findViewById(R.id.mainFindByOccupationButton);
        findByOrganizationButton = findViewById(R.id.mainFindByOrganizationButton);
        findByRaceButton = findViewById(R.id.mainFindByRaceButton);
        addPersonButton = findViewById(R.id.mainAddPersonButton);
        addLocationButton = findViewById(R.id.mainAddLocationButton);
        addOrganizationButton = findViewById(R.id.mainAddOrganizationButton);
        addQuestButton = findViewById(R.id.mainAddQuestButton);
        questList = findViewById(R.id.mainQuestListView);
        fileMenu = findViewById(R.id.fileMenu);
        addMenu = findViewById(R.id.addMenu);
    }

    private void defineSerachListeners() {
        findByNameButton.setOnClickListener(v -> setActivityChange(FindByNameActivity.class));
        findByLocationButton.setOnClickListener(v -> setActivityChange(ViewLocationsActivity.class));
        findByOccupationButton.setOnClickListener(v -> setActivityChange(FindByOccupationActivity.class));
        findByOrganizationButton.setOnClickListener(v -> setActivityChange(FindByOrganizationActivity.class));
        findByRaceButton.setOnClickListener(v -> setActivityChange(FindByRaceActivity.class));
    }

    private void defineAddObjectListeners() {
        addPersonButton.setOnClickListener(v -> setActivityChange(AddPersonActivity.class));
        addLocationButton.setOnClickListener(v -> setActivityChange(AddLocationActivity.class));
        addOrganizationButton.setOnClickListener(v -> setActivityChange(AddOrganizationActivity.class));
        addQuestButton.setOnClickListener(v -> setActivityChange(AddQuestActivity.class));
    }

    private void defineFileListeners() {
        newCampaignButton.setOnClickListener(v -> setActivityChange(CreateCampaignActivity.class));
        saveButton.setOnClickListener(
                v -> CampaignReaderWriter.save((String)campiagnList.getSelectedItem(), this));
        loadButton.setOnClickListener(
                v -> {
                    CampaignReaderWriter.load((String)campiagnList.getSelectedItem(), this);
                    refreshActivity();
                });
        deleteCampaignButton.setOnClickListener(
                v -> CampaignReaderWriter.delete(
                        (String)campiagnList.getSelectedItem(), this));
    }

    private void defineMenuListeners() {
        fileButton.setOnClickListener(getDisableMenuListener(fileMenu));
        addButton.setOnClickListener(getDisableMenuListener(addMenu));
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
                new ArrayList<>(CampaignReaderWriter.getExistingCampaigns(this)));
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
