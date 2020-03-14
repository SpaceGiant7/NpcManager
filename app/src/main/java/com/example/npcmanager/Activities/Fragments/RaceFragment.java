package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class RaceFragment extends ViewItemFragment {
    private TextView name;
    private TextView description;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        name = view.findViewById(R.id.viewRaceName);
        description = view.findViewById(R.id.viewRaceDescription);
        return view;
    }

    @Override
    protected void setItem(String item) {
        ApplicationModels.getRaceModel().getItemMaybe(item)
                .ifPresent(this::setRace);
    }

    private void setRace(Race race) {
        name.setText(race.getName());
        description.setText(race.getDescription());
    }

    @Override
    protected BaseModel getModel() {
        return ApplicationModels.getRaceModel();
    }

    @Override
    protected void clearSelection() {
        name.setText("");
        description.setText("");
    }

    private Race createRace() {
        return new Race(
                name.getText().toString(),
                description.getText().toString());
    }

    @Override
    protected void createItem() {
        ApplicationModelUpdater.addRace(createRace(), getContext());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceRace(oldItem, createRace(), getContext());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeRace(item, getContext());
    }

    @Override
    protected int getNewButtonId() {
        return R.id.viewRaceNewButton;
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewRaceSaveButton;
    }

    @Override
    protected int getFindByButtonId() {
        return R.id.viewRaceFindByButton;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_races;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.viewRaceList;
    }

    @Override
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getRaceModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.raceKey;
    }
}
