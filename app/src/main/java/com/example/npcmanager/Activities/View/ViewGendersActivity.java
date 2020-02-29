package com.example.npcmanager.Activities.View;

import android.os.Bundle;
import android.widget.TextView;

import com.example.npcmanager.Activities.FindBy.FindByGenderActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

public class ViewGendersActivity extends ViewItemActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = findViewById(R.id.viewGenderName);
    }


    @Override
    protected void setItem(String item) {
        ApplicationModels.getGenderModel().getItemMaybe(item)
                .ifPresent(this::setGender);

    }

    private void setGender(Gender gender) {
        name.setText(gender.getName());
    }

    @Override
    protected BaseModel getModel() {
        return ApplicationModels.getGenderModel();
    }

    @Override
    protected void clearSelection() {
        name.setText("");
    }

    private Gender createGender() {
        return Gender.of(
                name.getText().toString());
    }

    @Override
    protected void createItem() {
        ApplicationModelUpdater.addGender(createGender(), this);
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceGender(oldItem, createGender(), this);
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeGender(item, this);
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewGenderSaveButton;
    }

    @Override
    protected int getDeleteButtonId() {
        return R.id.viewGenderDeleteButton;
    }

    @Override
    protected int getFindByButtonId() {
        return R.id.viewGenderFindByButton;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_genders;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.viewGenderList;
    }

    @Override
    protected Class getFindByActivityClass() {
        return FindByGenderActivity.class;
    }

    @Override
    protected Serializable getSelectedItem(String selectedItem) {
        return ApplicationModels.getGenderModel().getItemMaybe(selectedItem).get();
    }

    @Override
    protected String getSerializationKey() {
        return ActivityUtilities.genderKey;
    }
}
