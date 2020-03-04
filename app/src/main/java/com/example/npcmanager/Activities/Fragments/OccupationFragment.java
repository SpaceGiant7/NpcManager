package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.npcmanager.Activities.FindBy.FindByOccupationActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class OccupationFragment extends ViewItemFragment{
    private TextView name;
    private TextView description;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        name = view.findViewById(R.id.viewOccupationName);
        description = view.findViewById(R.id.viewOccupationDescription);
        return view;
    }


    @Override
    protected void setItem(String item) {
        ApplicationModels.getOccupationModel().getItemMaybe(item)
                .ifPresent(this::setOccupation);

    }

    private void setOccupation(Occupation occupation) {
        name.setText(occupation.getName());
        description.setText(occupation.getDescription());
    }

    @Override
    protected BaseModel getModel() {
        return ApplicationModels.getOccupationModel();
    }

    @Override
    protected void clearSelection() {
        name.setText("");
        description.setText("");
    }

    private Occupation createOccupation() {
        return new Occupation(
                name.getText().toString(),
                description.getText().toString());
    }

    @Override
    protected void createItem() {
        ApplicationModelUpdater.addOccupation(createOccupation(), getContext());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceOccupation(oldItem, createOccupation(), getContext());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeOccupation(item, getContext());
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewOccupationSaveButton;
    }

    @Override
    protected int getFindByButtonId() {
        return R.id.viewOccupationFindByButton;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_occupations;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.viewOccupationList;
    }

    @Override
    protected Class getFindByActivityClass() {
        return FindByOccupationActivity.class;
    }

    @Override
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getOccupationModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.occupationKey;
    }
}
