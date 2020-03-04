package com.example.npcmanager.Activities.View;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.HomeButtonActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapter;
import com.example.npcmanager.Models.BaseModel;

import java.io.Serializable;
import java.util.Optional;

public abstract class ViewItemActivity extends HomeButtonActivity {

    protected Optional<String> selectedItem = Optional.empty();
    private RecyclerView recyclerView;
    RecyclerAdapter adapter;
    private Button saveButton;
    private Button findByButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        recyclerView = findViewById(getRecyclerViewId());
        saveButton = findViewById(getSaveButtonId());
        findByButton = findViewById(getFindByButtonId());

        setupRecyclerView();

        findByButton.setOnClickListener(v -> this.clickFindBy());
        saveButton.setOnClickListener(v -> this.clickSave());
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(
                () -> getModel().getAllItems(),
                item -> selectItem(item.getIdentifier()),
                item -> deleteItem(item.getIdentifier()),
                this);
        recyclerView.setAdapter(adapter);
    }

    void deleteItem(String item) {
        removeItem(item);
        adapter.reloadItems();
        clearSelectedItem();
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
                        this, getFindByActivityClass(), getSelectedItem(item), getSerializationKey()));
    }

    protected abstract void setItem(String item);

    protected abstract BaseModel getModel();

    protected abstract void clearSelection();

    protected abstract void createItem();

    protected abstract void replaceItem(String oldItem);

    protected abstract void removeItem(String item);

    protected abstract Class getFindByActivityClass();

    protected abstract int getSaveButtonId();
    protected abstract int getFindByButtonId();
    protected abstract int getContentView();
    protected abstract int getRecyclerViewId();
    protected abstract Serializable getSelectedItem(String selectedItem);
    protected abstract String getSerializationKey();
}
