package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class OrganizationFragment extends ViewItemFragment {
    private TextView name;
    private Spinner locationSelector;
    private TextView description;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        name = view.findViewById(R.id.viewOrganizationName);
        locationSelector = view.findViewById(R.id.viewOrganizationsLocationSelector);
        description = view.findViewById(R.id.viewOrganizationDescription);
        setLocationSelection(Location.None());
        return view;

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
        ArrayAdapter<BaseItem> locationAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getListWithNone());
        locationSelector.setAdapter(locationAdapter);
        locationSelector.setSelection(locationAdapter.getPosition(location));
    }

    private void updateLocations() {
        BaseItem selectedItem = (BaseItem) locationSelector.getSelectedItem();
        ArrayAdapter<BaseItem> locationAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                ApplicationModels.getLocationModel().getListWithNone());
        locationSelector.setAdapter(locationAdapter);
        locationSelector.setSelection(locationAdapter.getPosition(selectedItem));
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
        ApplicationModelUpdater.addOrganization(createOrganization(), getContext());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceOrganization(oldItem, createOrganization(), getContext());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeOrganization(item, getContext());
    }

    @Override
    protected int getNewButtonId() {
        return R.id.viewOrganizationNewButton;
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewOrganizationSaveButton;
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
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getOrganizationModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.organizationKey;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            updateLocations();
        }
    }
}
