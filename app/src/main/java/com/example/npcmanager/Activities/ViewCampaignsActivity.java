package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapters.BaseItemRecyclerAdapter;
import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.Optional;

public class ViewCampaignsActivity extends AppCompatActivity {

    protected Optional<String> selectedCampaign = Optional.empty();
    BaseItemRecyclerAdapter adapter;
    private TextView name;
    private RecyclerView recyclerView;
    private ImageButton newButton;
    private ImageButton saveButton;
    private ImageButton loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_campaigns);
        name = findViewById(R.id.viewCampaignName);
        recyclerView = findViewById(R.id.viewCampaignList);
        newButton = findViewById(R.id.viewCampaignNewButton);
        saveButton = findViewById(R.id.viewCampaignSaveButton);
        loadButton = findViewById(R.id.viewCampaignLoadButton);

        setupRecyclerView();

        newButton.setOnClickListener(v -> clickNew());
        saveButton.setOnClickListener(v -> clickSave());
        loadButton.setOnClickListener(v -> clickLoad());
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseItemRecyclerAdapter(
                () -> CampaignReaderWriter.getExistingCampaigns(this),
                item -> selectCampaign(item.getIdentifier()),
                item -> deleteCampaign(item.getIdentifier()),
                this);
        recyclerView.setAdapter(adapter);
    }

    private void selectCampaign(String item) {
        name.setText(item);
        setSelectedCampaign(item);
    }

    private void deleteCampaign(String item) {
        CampaignReaderWriter.delete(item, this);
        clearSelectedCampaign();
        adapter.reloadItems();
    }

    private void setSelectedCampaign(String campaign) {
        selectedCampaign = Optional.of(campaign);
    }

    private void clearSelectedCampaign() {
        name.setText("");
        selectedCampaign = Optional.empty();
    }

    void clickSave() {
        saveCampaign();
    }

    private void saveCampaign() {
        if (selectedCampaign.isPresent()) {
            CampaignReaderWriter.rename(selectedCampaign.get(), name.getText().toString(), this);
        } else {
            ApplicationModels.resetModels();
            CampaignReaderWriter.save(name.getText().toString(), this);
        }
        adapter.reloadItems();
    }

    void clickLoad() {
        selectedCampaign.ifPresent(this::loadCampaign);
    }

    private void loadCampaign(String name) {
        CampaignReaderWriter.load(name, this);
        ApplicationModels.setCampaignName(name);
        ActivityUtilities.loadMainActivity(this);
    }

    void clickNew() {
        clearSelectedCampaign();
    }
}
