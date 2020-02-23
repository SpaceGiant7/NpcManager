package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStorage.CampaignReaderWriter;
import com.example.npcmanager.R;

import java.util.Optional;

public class ViewCampaignsActivity extends AppCompatActivity {

    protected Optional<String> selectedCampaign = Optional.empty();
    RecyclerAdapter adapter;
    private TextView name;
    private RecyclerView recyclerView;
    private Button saveButton;
    private Button deleteButton;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_campaigns);
        name = findViewById(R.id.viewCampaignName);
        recyclerView = findViewById(R.id.viewCampaignList);
        saveButton = findViewById(R.id.viewCampaignSaveButton);
        deleteButton = findViewById(R.id.viewCampaignDeleteButton);
        loadButton = findViewById(R.id.viewCampaignLoadButton);

        setupRecyclerView();

        saveButton.setOnClickListener(v -> clickSave());
        deleteButton.setOnClickListener(v -> clickDelete());
        loadButton.setOnClickListener(v -> clickLoad());
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(
                () -> CampaignReaderWriter.getExistingCampaigns(this),
                item -> selectCampaign(item.getIdentifier()));
        recyclerView.setAdapter(adapter);
    }

    private void selectCampaign(String item) {
        name.setText(item);
        setSelectedCampaign(item);
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
        clearSelectedCampaign();
    }

    private void saveCampaign() {
        if (selectedCampaign.isPresent()) {
            CampaignReaderWriter.rename(selectedCampaign.get(), name.getText().toString(), this);
        } else {
            CampaignReaderWriter.save(name.getText().toString(), this);
        }
        adapter.reloadItems();
    }

    void clickDelete() {
        CampaignReaderWriter.delete(name.getText().toString(), this);
        clearSelectedCampaign();
        adapter.reloadItems();
    }

    void clickLoad() {
        selectedCampaign.ifPresent(this::loadCampaign);
    }

    private void loadCampaign(String name) {
        CampaignReaderWriter.load(name, this);
        ActivityUtilities.loadMainActivityWithCampaignName(this, name);
    }
}
