package com.example.npcmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.Activities.FindBy.FindByNameActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.QuestSelectorListener;
import com.example.npcmanager.Activities.View.ViewCampaignsActivity;
import com.example.npcmanager.Activities.View.ViewGendersActivity;
import com.example.npcmanager.Activities.View.ViewLocationsActivity;
import com.example.npcmanager.Activities.View.ViewOccupationsActivity;
import com.example.npcmanager.Activities.View.ViewOrganizationsActivity;
import com.example.npcmanager.Activities.View.ViewRacesActivity;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton addButton;
    ImageButton fileButton;
    Button findByNameButton;
    Button findByLocationButton;
    Button findByOccupationButton;
    Button findByOrganizationButton;
    Button findByRaceButton;
    Button findByGenderButton;
    Button addPersonButton;
    Button addLocationButton;
    Button addOrganizationButton;
    Button addQuestButton;
    ListView questList;
    TextView campiagnName;
    LinearLayout addMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getElementReferences();
        defineMenuListeners();
        defineAddObjectListeners();
        defineSearchListeners();

        campiagnName.setText(ApplicationModels.getCampaignName());

        fileButton.setOnClickListener(v -> ActivityUtilities.loadActivity(
                this, ViewCampaignsActivity.class));

        questList.setOnItemClickListener(new QuestSelectorListener(this, ViewQuestActivity.class));
    }

    private void getElementReferences() {
        campiagnName = findViewById(R.id.mainCampaignText);
        addButton = findViewById(R.id.addButton);
        fileButton = findViewById(R.id.fileButton);
        findByNameButton = findViewById(R.id.mainFindByNameButton);
        findByLocationButton = findViewById(R.id.mainFindByLocationButton);
        findByOccupationButton = findViewById(R.id.mainFindByOccupationButton);
        findByOrganizationButton = findViewById(R.id.mainFindByOrganizationButton);
        findByRaceButton = findViewById(R.id.mainFindByRaceButton);
        findByGenderButton = findViewById(R.id.mainFindByGenderButton);
        addPersonButton = findViewById(R.id.mainAddPersonButton);
        addLocationButton = findViewById(R.id.mainAddLocationButton);
        addOrganizationButton = findViewById(R.id.mainAddOrganizationButton);
        addQuestButton = findViewById(R.id.mainAddQuestButton);
        questList = findViewById(R.id.mainQuestListView);
        addMenu = findViewById(R.id.addMenu);
    }

    private void defineSearchListeners() {
        findByNameButton.setOnClickListener(v -> setActivityChange(FindByNameActivity.class));
        findByLocationButton.setOnClickListener(v -> setActivityChange(ViewLocationsActivity.class));
        findByOccupationButton.setOnClickListener(v -> setActivityChange(ViewOccupationsActivity.class));
        findByOrganizationButton.setOnClickListener(v -> setActivityChange(ViewOrganizationsActivity.class));
        findByRaceButton.setOnClickListener(v -> setActivityChange(ViewRacesActivity.class));
        findByGenderButton.setOnClickListener(v -> setActivityChange(ViewGendersActivity.class));
    }

    private void defineAddObjectListeners() {
        addPersonButton.setOnClickListener(v -> setActivityChange(AddPersonActivity.class));
        addLocationButton.setOnClickListener(v -> setActivityChange(AddLocationActivity.class));
        addOrganizationButton.setOnClickListener(v -> setActivityChange(AddOrganizationActivity.class));
        addQuestButton.setOnClickListener(v -> setActivityChange(AddQuestActivity.class));
    }

    private void defineMenuListeners() {
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
                new ArrayList<>(ApplicationModels.getQuestModel().getAllItems()));
        questList.setAdapter(nameAdapter);
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
