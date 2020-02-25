package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.npcmanager.Activities.FindBy.FindByLocationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class ViewLocationsActivity extends ViewItemActivity {

    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = findViewById(R.id.viewLocationName);
        description = findViewById(R.id.viewLocationDescription);
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
        ApplicationModelUpdater.addLocation(createLocation());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceLocation(oldItem, createLocation());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeLocation(item);
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewLocationSaveButton;
    }

    @Override
    protected int getDeleteButtonId() {
        return R.id.viewLocationDeleteButton;
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
