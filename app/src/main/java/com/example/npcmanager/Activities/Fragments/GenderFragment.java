package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.npcmanager.Activities.FindBy.FindByGenderActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.example.npcmanager.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenderFragment extends ViewItemFragment {

    private TextView name;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        name = view.findViewById(R.id.viewGenderName);
        return view;
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
        ApplicationModelUpdater.addGender(createGender(), getContext());
    }

    @Override
    protected void replaceItem(String oldItem) {
        ApplicationModelUpdater.replaceGender(oldItem, createGender(), getContext());
    }

    @Override
    protected void removeItem(String item) {
        ApplicationModelUpdater.removeGender(item, getContext());
    }

    @Override
    protected int getNewButtonId() {
        return R.id.viewGenderNewButton;
    }

    @Override
    protected int getSaveButtonId() {
        return R.id.viewGenderSaveButton;
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


