package com.example.npcmanager.Activities.Utilities.RecyclerAdapters;

import com.example.npcmanager.DataStructures.BaseItem;

import java.util.List;

public class AddItemSelector {
    private String label;
    private BaseItem selectedItem;
    private List<BaseItem> baseItems;

    public AddItemSelector(String label, BaseItem selectedItem, List<BaseItem> baseItems) {
        this.label = label;
        this.selectedItem = selectedItem;
        this.baseItems = baseItems;
    }

    public String getLabel() {
        return label;
    }

    public BaseItem getSelectedItem() {
        return selectedItem;
    }

    public List<BaseItem> getBaseItems() {
        return baseItems;
    }
}
