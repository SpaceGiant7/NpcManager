package com.example.npcmanager.Models;


import com.example.npcmanager.DataStructures.BaseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import Constants.NpcConstants;

public abstract class BaseModel<T extends BaseItem> {
    private static final Logger LOGGER = Logger.getLogger(BaseModel.class.getName());
    List<T> list;

    public BaseModel() {
        list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public List<T> getListWithNone() {
        List<T> newList = new ArrayList<T>(list);
        newList.add(0, getNone());
        return newList;
    }

    public Optional<T> getItemMaybe(String identifier) {
        return getList().stream()
                .filter(t -> t.getIdentifier()
                        .equals(identifier))
                .findFirst();
    }

    protected abstract T getNone();

    protected void addItem(T newItem) {
        if (canItemBeAdded(newItem)) {
            list.add(newItem);
            list.sort(BaseModel::compareItems);
            logAdd(newItem);
        } else {
            logFailedAdd(newItem);
        }
    }

    private boolean canItemBeAdded(T item) {
        return !itemExists(item) && !item.getIdentifier().equals(NpcConstants.NONE);
    }

    protected void removeItem(T item) {
        if (itemExists(item)) {
            list.remove(item);
            logRemove(item);
        } else {
            logFailedRemove(item);
        }
    }

    private static int compareItems(BaseItem item1, BaseItem item2) {
        return item1.getIdentifier().toLowerCase().compareTo(
                item2.getIdentifier().toLowerCase());
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
