package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class LocationFragment extends ViewItemFragment {
    private TextView name;
    private TextView description;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        name = view.findViewById(R.id.viewLocationName);
        description = view.findViewById(R.id.viewLocationDescription);
        return view;
    }


    @Override
    protected void setItem(String item) {
        ApplicationModels.getLocationModel().getItemMaybe(item)
                .ifPresent(this::setLocation);

    }

    private void setLocation(Location location) {
        name.setText(location.getName());
        description.setText(location.getDescription());
    }

    @Override
    protected BaseModel getModel() {
        return ApplicationModels.getLocationModel();
    }

    @Override
    protected void clearSelection() {
        name.setText("");
        description.setText("");
    }

    private Location createLocation() {
        return Location.of(
                name.getText().toString(),
                description.getText().toString());
    }

    @Override
    protected void createItem() {
        ApplicationModelUpdater.addLocation(createLocation(), getContext());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceLocation(oldItem, createLocation(), getContext());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeLocation(item, getContext());
    }

    @Override
    protected int getNewButtonId() {
        return R.id.viewLocationNewButton;
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewLocationSaveButton;
    }

    @Override
    protected int getFindByButtonId() {
        return R.id.viewLocationFindByButton;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_locations;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.viewLocationList;
    }

    @Override
    protected Class getFindByActivityClass() {
        return FindByLocationActivity.class;
    }

    @Override
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getLocationModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.locationKey;
    }


}
