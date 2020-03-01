package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapter;
import com.example.npcmanager.Models.BaseModel;

import java.io.Serializable;
import java.util.Optional;

public abstract class ViewItemFragment extends Fragment {

    protected Optional<String> selectedItem = Optional.empty();
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private Button saveButton;
    private Button deleteButton;
    private Button findByButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        recyclerView = view.findViewById(getRecyclerViewId());
        saveButton = view.findViewById(getSaveButtonId());
        deleteButton = view.findViewById(getDeleteButtonId());
        findByButton = view.findViewById(getFindByButtonId());

        setupRecyclerView();

        findByButton.setOnClickListener(v -> this.clickFindBy());
        saveButton.setOnClickListener(v -> this.clickSave());
        deleteButton.setOnClickListener(v -> this.clickDelete());
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(
                () -> getModel().getAllItems(),
                item -> selectItem(item.getIdentifier()));
        recyclerView.setAdapter(adapter);
    }

    void selectItem(String item) {
        setItem(item);
        setSelectedItem(item);
    }

    private void setSelectedItem(String item) {
        selectedItem = Optional.of(item);
    }

    private void clearSelectedItem() {
        selectedItem = Optional.empty();
        clearSelection();
    }

    private void clickSave() {
        if (selectedItem.isPresent()) {
            replaceItem(selectedItem.get());
        } else {
            createItem();
        }
        adapter.reloadItems();
        clearSelectedItem();
    }

    private void clickDelete() {
        selectedItem.ifPresent(this::removeItem);
        adapter.reloadItems();
        clearSelectedItem();
    }

    private void clickFindBy() {
        selectedItem.ifPresent(item ->
                ActivityUtilities.loadActivityWithExtra(
                        getActivity(), getFindByActivityClass(), getSelectedItem(item), getSerializationKey()));
    }

    protected abstract void setItem(String item);

    protected abstract BaseModel getModel();

    protected abstract void clearSelection();

    protected abstract void createItem();

    protected abstract void replaceItem(String oldItem);

    protected abstract void removeItem(String item);

    protected abstract Class getFindByActivityClass();

    protected abstract int getSaveButtonId();
    protected abstract int getDeleteButtonId();
    protected abstract int getFindByButtonId();
    protected abstract int getContentView();
    protected abstract int getRecyclerViewId();
    protected abstract Serializable getSelectedItem(String selectedItem);
    protected abstract String getSerializationKey();



}
