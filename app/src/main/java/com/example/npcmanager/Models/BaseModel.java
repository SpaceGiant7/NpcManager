package com.example.npcmanager.Models;


import com.example.npcmanager.DataStructures.BaseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseModel<T extends BaseItem> {
    private static final Logger LOGGER = Logger.getLogger(BaseModel.class.getName());
    List<T> list;

    public BaseModel() {
        list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    protected void addItem(T newItem) {
        if (!itemExists(newItem)) {
            list.add(newItem);
            logAdd(newItem);
        } else {
            logFailedAdd(newItem);
        }

    }

    protected void removeItem(T item) {
        if (itemExists(item)) {
            list.remove(item);
            logRemove(item);
        } else {
            logFailedRemove(item);
        }
    }

    protected Boolean itemExists(T item) {
        return list.contains(item);
    }

    protected void replaceList(List<T> newItems) {
        list = new ArrayList<>(newItems);
    }

    protected int getNumberOfItems() {
        return list.size();
    }

    private void logAdd(T item) {
        LOGGER.log(Level.INFO, String.format("Added %s to model: %s", item.getClass(), item));
    }

    private void logRemove(T item) {
        LOGGER.log(Level.INFO, String.format("Removed %s from model: %s", item.getClass(), item));
    }

    private void logFailedAdd(T item) {
        LOGGER.log(Level.INFO, String.format("Failed to add %s to model: %s", item.getClass(), item));
    }

    private void logFailedRemove(T item) {
        LOGGER.log(Level.INFO, String.format("Failed to remove %s from model: %s", item.getClass(), item));
    }
}
