package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.Activities.FindBy.FindByOrganizationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class ViewOrganizationsActivity extends ViewItemActivity {

    private TextView name;
    private Spinner locationSelector;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = findViewById(R.id.viewOrganizationName);
        locationSelector = findViewById(R.id.viewOrganizationsLocationSelector);
        description = findViewById(R.id.viewOrganizationDescription);
        setLocationSelection(Location.None());

    }

    @Override
    protected void setItem(String item) {
        ApplicationModels.getOrganizationModel().getItemMaybe(item)
                .ifPresent(this::setOrganization);

    }

    private void setOrganization(Organization organization) {
        name.setText(organization.getName());
        setLocationSelection(organization.getLocation());
        description.setText(organization.getDescription());
    }

    private void setLocationSelection(Location location) {
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getListWithNone());

        locationSelector.setAdapter(locationAdapter);
        locationSelector.setSelection(locationAdapter.getPosition(location));
    }

    @Override
    protected BaseModel getModel() {
        return ApplicationModels.getOrganizationModel();
    }

    @Override
    protected void clearSelection() {
        name.setText("");
        setLocationSelection(Location.None());
        description.setText("");
    }

    private Organization createOrganization() {
        return new Organization(
                name.getText().toString(),
                (Location) locationSelector.getSelectedItem(),
                description.getText().toString());
    }

    @Override
    protected void createItem() {
        ApplicationModelUpdater.addOrganization(createOrganization());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceOrganization(oldItem, createOrganization());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeOrganization(item);
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewOrganizationSaveButton;
    }

    @Override
    protected int getDeleteButtonId() {
        return R.id.viewOrganizationDeleteButton;
    }

    @Override
    protected int getFindByButtonId() {
        return R.id.viewOrganizationFindByButton;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_organizations;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.viewOrganizationList;
    }

    @Override
    protected Class getFindByActivityClass() {
        return FindByOrganizationActivity.class;
    }

    @Override
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getOrganizationModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.organizationKey;
    }
}
